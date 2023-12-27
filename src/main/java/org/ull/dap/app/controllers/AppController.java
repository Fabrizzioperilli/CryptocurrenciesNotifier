package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
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
        } else if (e.getActionCommand().equals("START")) {
            System.out.println("Start");
            if (checkAllUsersHaveCryptos()) {
                System.out.println("All users have cryptos");
                startBackground();
            } else {
                JOptionPane.showMessageDialog(null, "You must select at least 1 crypto for each user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("ADD_BITCOIN")) {
            addCrypto("bitcoin");
        } else if (e.getActionCommand().equals("ADD_ETHEREUM")) {
            addCrypto("ethereum");
        } else if (e.getActionCommand().equals("ADD_LITECOIN")) {
            addCrypto("litecoin");
        } else if (e.getActionCommand().equals("ADD_CARDANO")) {
            addCrypto("cardano");
        } else if (e.getActionCommand().equals("DELETE_BITCOIN")) {
            deleteCrypto("bitcoin");
        } else if (e.getActionCommand().equals("DELETE_ETHEREUM")) {
            deleteCrypto("ethereum");
        } else if (e.getActionCommand().equals("DELETE_LITECOIN")) {
            deleteCrypto("litecoin");
        } else if (e.getActionCommand().equals("DELETE_CARDANO")) {
            deleteCrypto("cardano");
        }

    }

    private void login() {
        String[] usersSelected = ((MainView) view).getListUsers().getSelectedValuesList().toArray(new String[0]);

        if (usersSelected.length < 1) {
            JOptionPane.showMessageDialog(null, "You must select at least 1 users", "Error", JOptionPane.ERROR_MESSAGE);
            return;
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

    public boolean checkAllUsersHaveCryptos() {
        for (int i = 0; i < view.getUsersSelected().length; i++) {
            if (((User) notifier.getObservers().get(i)).getNameCryptos().size() < 1) {
                return false;
            }
        }
        return true;
    }

    private void startBackground() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                notifier.start();
                return null;
            }

            @Override
            protected void done() {
            }
        };
        worker.execute();
    }

    void addCrypto(String name) {
        for (IObserver observer : notifier.getObservers()) {
            if (((User) observer).getName().equals(((MainView) view).getComboBoxUsersSelected().getSelectedItem())) {
                if (!observer.getNameCryptos().contains(name)) {
                    observer.addCrypto(name);
                    System.out.println("Added " + name + " to " + ((User) observer).getName());
                    ((MainView)view).enableButtons(name, false);
                }
            }
        }
    }


    void deleteCrypto(String name) {
        for (IObserver observer : notifier.getObservers()) {
            if (((User) observer).getName().equals(((MainView) view).getComboBoxUsersSelected().getSelectedItem())) {
                if (observer.getNameCryptos().contains(name)) {
                    observer.deleteCrypto(name);
                    System.out.println("Deleted " + name + " to " + ((User) observer).getName());
                    ((MainView)view).enableButtons(name, true);
                }
            }
        }
    }


}
