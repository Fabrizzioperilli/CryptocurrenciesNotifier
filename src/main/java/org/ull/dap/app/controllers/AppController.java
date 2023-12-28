package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.MainView;
import org.ull.dap.app.views.Notification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AppController implements ActionListener {

    private CryptocurrencyNotifier notifier;

    private IView view;

    public AppController(CryptocurrencyNotifier notifier, IView view) {
        this.notifier = notifier;
        this.view = view;
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
        String[] usersSelected = view.getUsersSelected();

        if (usersSelected.length < 1) {
            JOptionPane.showMessageDialog(null, "You must select at least 1 users", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        view.setUsersSelected(usersSelected);
        System.out.println(Arrays.toString(view.getUsersSelected()));
        ((MainView) view).nextWindow();
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

    public void suscribeUsers(String[] usersSelected) {
        for (int i = 0; i < usersSelected.length; i++) {
            notifier.subscribe(new User(usersSelected[i], i, new ArrayList<>()));
        }
    }

    public boolean checkAllUsersHaveCryptos() {
        for (int i = 0; i < view.getUsersSelected().length; i++) {
            if (notifier.getObservers().get(i).getNameCryptos().size() < 1) {
                return false;
            }
        }
        return true;
    }


    public void start() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (IObserver observer : notifier.getObservers()) {
            ((User) observer).setNotification(new Notification());
            ((User) observer).getNotification().setVisible(true);
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
            System.out.println("\n");
        }, 0, 40, TimeUnit.SECONDS);
    }

    private void startBackground() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
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
        if (((User) observer).getName().equals(((MainView) view).getComboBoxUsersSelected().getSelectedItem())) {
            if (isDelete == observer.getNameCryptos().contains(name)) {
                if (isDelete) {
                    observer.deleteCrypto(name);
                } else {
                    observer.addCrypto(name);
                }
                System.out.println(action + " " + name + " to " + ((User) observer).getName());
                view.enableButtons(name, isDelete);
            }
        }
    }
}
