package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;
import org.ull.dap.app.views.INotification;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.desktop.ViewDesktop;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppController implements ActionListener {

    private CryptocurrencyNotifier notifier;

    private IView view;

    private final Map<IObserver, INotification> notificationsWithUsers;

    private final List<String> usersAvailable;

    public AppController(CryptocurrencyNotifier notifier, IView view) {
        this.notifier = notifier;
        this.view = view;
        this.notificationsWithUsers = new HashMap<>();
        usersAvailable = Arrays.asList("USER_1", "USER_2", "USER_3", "USER_4", "USER_5");
    }

    public CryptocurrencyNotifier getNotifier() {
        return notifier;
    }

    public void setNotifier(CryptocurrencyNotifier notifier) {
        this.notifier = notifier;
    }

    public List<String> getUsersAvailable() {
        return usersAvailable;
    }

    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "LOGIN" -> handleLogin();
            case "START" -> handleStart();
            default -> handleCryptoAction(e.getActionCommand());
        }
    }

    private void handleLogin() {
        List<String> usersSelected = view.getUsersSelected();

        if (usersSelected.size() < 1) {
            JOptionPane.showMessageDialog(null, "You must select at least 1 users", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        suscribeUsers(usersSelected);
        view.windowSelectCryptos();
    }

    private void handleStart() {
        if (checkAllUsersHaveCryptos()) {
            start();
        } else {
            showError();
        }
    }

    private void handleCryptoAction(String actionCommand) {
        String cryptoName = actionCommand.split("_")[1].toLowerCase();
        if (actionCommand.startsWith("ADD")) {
            addCrypto(cryptoName);
        } else if (actionCommand.startsWith("DELETE")) {
            deleteCrypto(cryptoName);
        }
    }

    private void showError() {
        JOptionPane.showMessageDialog(null, "You must select at least 1 crypto for each user", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void suscribeUsers(List<String> usersSelected) {
        for (int i = 0; i < usersSelected.size(); i++) {
            notifier.subscribe(new User(usersSelected.get(i), i, new ArrayList<>()));
        }
    }

    public boolean checkAllUsersHaveCryptos() {
        for (int i = 0; i < view.getUsersSelected().size(); i++) {
            if (notifier.getObservers().get(i).getNameCryptos().size() < 1) {
                return false;
            }
        }
        return true;
    }


    public void start() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < notifier.getObservers().size(); i++) {
            IObserver user = notifier.getObservers().get(i);
            INotification notification = view.getNotifications().get(i);
            notificationsWithUsers.put(user, notification);
        }
        executorService.scheduleAtFixedRate(() -> {
            notifier.getAssets().clear();
            try {
                for (String nameCrypto : notifier.getNamesCryptocurrencies()) {
                    notifier.getAssets().add(notifier.getConnectionAPI().getAssetData(nameCrypto));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            notifier.notifyObservers();
            if (view instanceof ViewDesktop) {
                ((ViewDesktop) view).getDashboardDesktop().updateData(notifier.getAssets());
            }

            for (int i = 0; i < notifier.getObservers().size(); i++) {
                IObserver user = notifier.getObservers().get(i);
                notificationsWithUsers.get(user).showNotification(user.getMessagesToNotify(), notifier.getCryptoNameImage());
            }
            notifier.getObservers().forEach(v -> v.getMessagesToNotify().clear());

        }, 0, 40, TimeUnit.SECONDS);
    }


    public void addCrypto(String name) {
        for (IObserver observer : notifier.getObservers()) {
            handleCryptoOperation(name, observer, false);
        }
    }

    public void deleteCrypto(String name) {
        for (IObserver observer : notifier.getObservers()) {
            handleCryptoOperation(name, observer, true);
        }
    }

    private void handleCryptoOperation(String name, IObserver observer, boolean isDelete) {
        if (observer.getName().equals(view.getUserComboBoxString())) {
            if (isDelete == observer.getNameCryptos().contains(name)) {
                if (isDelete) {
                    observer.deleteCrypto(name);
                } else {
                    observer.addCrypto(name);
                }

                if (view instanceof ViewDesktop) {
                    ((ViewDesktop) view).enableButtons(name, isDelete);
                }
            }
        }
    }


}
