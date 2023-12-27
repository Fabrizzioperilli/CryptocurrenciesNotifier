package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.notifiers.Observable;
import org.ull.dap.app.models.users.User;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
        if (e.getActionCommand().equals("LOGIN")) {
            System.out.println("LOGIN");
            login();
        }
    }

    private void login() {
        String[] usersSelected = ((MainView) view).getListUsers().getSelectedValuesList().toArray(new String[0]);

        if (usersSelected.length < 1) {
            JOptionPane.showMessageDialog(null, "You must select at least 1 users", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ((MainView)view).setUsersSelected(usersSelected);
        System.out.println(Arrays.toString(view.getUsersSelected()));
        ((MainView) view).nextWindow();
    }

    public void suscribeUsers(String[] usersSelected) {
        for (int i = 0; i < usersSelected.length; i++) {
            notifier.subscribe(new User(usersSelected[i], i, new ArrayList<>()));
        }
    }
}
