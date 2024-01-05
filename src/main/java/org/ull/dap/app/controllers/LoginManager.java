package org.ull.dap.app.controllers;

import org.ull.dap.app.models.users.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User manager.
 */
public class LoginManager {
    private final AppController appController;

    /**
     * Instantiates a new User manager.
     *
     * @param appController the app controller
     */
    public LoginManager(AppController appController) {
        this.appController = appController;
    }

    /**
     * Handle login.
     */
    public void handleLogin() {
        List<String> usersSelected = appController.getView().getUsersSelected();

        if (usersSelected.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must select at least 1 users", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        suscribeUsers(usersSelected);
        appController.getView().windowSelectCryptos();
    }


    /**
     * Suscribe users.
     *
     * @param usersSelected the users selected
     */
    public void suscribeUsers(List<String> usersSelected) {
        for (int i = 0; i < usersSelected.size(); i++) {
            appController.getNotifier().subscribe(new User(usersSelected.get(i), i, new ArrayList<>()));
        }
    }

}
