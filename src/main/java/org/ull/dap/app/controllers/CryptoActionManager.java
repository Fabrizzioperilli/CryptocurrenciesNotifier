package org.ull.dap.app.controllers;

import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.views.desktop.DataDesktop;

/**
 * The type Crypto data manager.
 */
public class CryptoActionManager {
    private final AppController appController;

    /**
     * Instantiates a new Crypto data manager.
     *
     * @param appController the app controller
     */
    public CryptoActionManager(AppController appController) {
        this.appController = appController;
    }


    /**
     * Handle crypto action.
     *
     * @param actionCommand the action command
     */
    public void handleCryptoAction(String actionCommand) {
        String cryptoName = actionCommand.split("_")[1].toLowerCase();
        if (actionCommand.startsWith("ADD")) {
            addCrypto(cryptoName);
        } else if (actionCommand.startsWith("DELETE")) {
            deleteCrypto(cryptoName);
        }
    }

    private void addCrypto(String name) {
        for (IObserver observer : appController.getNotifier().getObservers()) {
            handleCryptoOperation(name, observer, false);
        }
    }

    /**
     * Delete crypto.
     *
     * @param name the name
     */
    private void deleteCrypto(String name) {
        for (IObserver observer : appController.getNotifier().getObservers()) {
            handleCryptoOperation(name, observer, true);
        }
    }

    private void handleCryptoOperation(String name, IObserver observer, boolean isDelete) {
        if (observer.getName().equals(appController.getView().getUserComboBoxString())) {
            if (isDelete == observer.getNameCryptos().contains(name)) {
                if (isDelete) {
                    observer.deleteCrypto(name);
                } else {
                    observer.addCrypto(name);
                }

                if (appController.getView() instanceof DataDesktop) {
                    ((DataDesktop) appController.getView()).enableButtons(name, isDelete);
                }
            }
        }
    }
}
