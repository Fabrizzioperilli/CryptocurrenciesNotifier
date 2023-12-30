package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;
import org.ull.dap.app.views.INotification;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.desktop.DesktopView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AppController implements ActionListener {

    private CryptocurrencyNotifier notifier;

    private IView view;

    private final Map<IObserver, INotification> notificationsWithUsers;

    public AppController(CryptocurrencyNotifier notifier, IView view) {
        this.notifier = notifier;
        this.view = view;
        this.notificationsWithUsers = new HashMap<>();
    }

    public CryptocurrencyNotifier getNotifier() {
        return notifier;
    }

    public void setNotifier(CryptocurrencyNotifier notifier) {
        this.notifier = notifier;
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
            case "ADD_BITCOIN", "DELETE_BITCOIN", "ADD_ETHEREUM", "DELETE_ETHEREUM", "ADD_LITECOIN", "DELETE_LITECOIN", "ADD_CARDANO", "DELETE_CARDANO" ->
                    handleCryptoAction(e.getActionCommand());
        }
    }

    private void handleLogin() {
        List<String> usersSelected = view.getUsersSelected();

        if (usersSelected.size() < 1) {
            JOptionPane.showMessageDialog(null, "You must select at least 1 users", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        view.setUsersSelected(usersSelected);
        System.out.println(view.getUsersSelected());
        ((DesktopView) view).nextWindow();
    }

    private void handleStart() {
        if (checkAllUsersHaveCryptos()) {
            System.out.println("All users have cryptos");
            startBackground();
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

    public void suscribeUsers(List<String>usersSelected) {
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
            notificationsWithUsers.put(user,notification);
        }
        ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(() -> {
            notifier.getAssets().clear();
            try {
                for (String nameCrypto : notifier.getNamesCryptocurrencies()) {
                    notifier.getAssets().add(notifier.getConnectionAPI().getAssetData(nameCrypto));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Assets:\n ");
            notifier.getAssets().forEach(v -> System.out.println(v.getData().getName() + " " + v.getData().getPriceUsd()));

            notifier.notifyObservers();

            for (int i = 0; i < notifier.getObservers().size(); i++) {
                IObserver user = notifier.getObservers().get(i);
                notificationsWithUsers.get(user).createNotify(user.getMessagesToNotify());
            }
            notifier.getObservers().forEach(v -> v.getMessagesToNotify().clear());
            System.out.println("\n");
        }, 0, 40, TimeUnit.SECONDS);
    }

    private void startBackground() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                start();
                return null;
            }
        };
        worker.execute();
    }

    private void addCrypto(String name) {
        for (IObserver observer : notifier.getObservers()) {
            handleCryptoOperation(name, observer, false, "Added");
        }
    }

    private void deleteCrypto(String name) {
        for (IObserver observer : notifier.getObservers()) {
            handleCryptoOperation(name, observer, true, "Deleted");
        }
    }

    private void handleCryptoOperation(String name, IObserver observer, boolean isDelete, String action) {
        if (observer.getName().equals(((DesktopView) view).getComboBoxUsersSelected().getSelectedItem())) {
            if (isDelete == observer.getNameCryptos().contains(name)) {
                if (isDelete) {
                    observer.deleteCrypto(name);
                } else {
                    observer.addCrypto(name);
                }
                System.out.println(action + " " + name + " to " + observer.getName());
                view.enableButtons(name, isDelete);
            }
        }
    }


}
