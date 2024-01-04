package org.ull.dap.app.controllers;

import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.views.INotification;
import org.ull.dap.app.views.desktop.ViewDesktop;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The type Start manager.
 */
public class StartManager {
    private final AppController appController;
    private static final int TIME_TO_UPDATE = 30;

    /**
     * Instantiates a new Start manager.
     *
     * @param appController the app controller
     */
    public StartManager(AppController appController) {
        this.appController = appController;
    }

    /**
     * Handle start.
     */
    public void handleStart() {
        if (checkAllUsersHaveCryptos()) {
            start();
        } else {
            showError();
        }
    }

    private void showError() {
        JOptionPane.showMessageDialog(null, "You must select at least 1 crypto for each user", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Check all users have cryptos boolean.
     *
     * @return the boolean
     */
    public boolean checkAllUsersHaveCryptos() {
        for (int i = 0; i < appController.getView().getUsersSelected().size(); i++) {
            if (appController.getNotifier().getObservers().get(i).getNameCryptos().size() < 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Start.
     */
    public void start() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < appController.getNotifier().getObservers().size(); i++) {
            IObserver user = appController.getNotifier().getObservers().get(i);
            INotification notification = appController.getView().getNotifications().get(i);
            appController.getNotificationsWithUsers().put(user, notification);
        }
        executorService.scheduleAtFixedRate(() -> {
            appController.getNotifier().getAssets().clear();
            try {
                for (String nameCrypto : appController.getNotifier().getNamesCryptocurrencies()) {
                    appController.getNotifier().getAssets().add(appController.getNotifier().getConnectionAPI().getAssetData(nameCrypto));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            appController.getNotifier().notifyObservers();
            if (appController.getView() instanceof ViewDesktop) {
                ((ViewDesktop) appController.getView()).getDashboardDesktop().updateData(appController.getNotifier().getAssets());
            }

            for (int i = 0; i < appController.getNotifier().getObservers().size(); i++) {
                IObserver user = appController.getNotifier().getObservers().get(i);
                appController.getNotificationsWithUsers().get(user).showNotification(user.getMessagesToNotify(), appController.getNotifier().getCryptoNameImage());
            }
            appController.getNotifier().getObservers().forEach(v -> v.getMessagesToNotify().clear());

        }, 0, TIME_TO_UPDATE, TimeUnit.SECONDS);
    }
}
