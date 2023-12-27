package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.Observable;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AppController implements ActionListener {

    private Observable notifier;

    private IView view;

    public AppController(Observable notifier, IView view) {
        this.notifier = notifier;
        this.view = view;
    }

    public Observable getNotifier() {
        return notifier;
    }

    public void setNotifier(Observable notifier) {
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
}
