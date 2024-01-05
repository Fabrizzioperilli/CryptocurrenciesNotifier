package org.ull.dap.app.controllers;

import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.views.INotificationView;
import org.ull.dap.app.views.IDataView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * The type App controller.
 */
public class AppController implements ActionListener {

    private CryptocurrencyNotifier notifier;

    private IDataView view;

    private final Map<IObserver, INotificationView> notificationsWithUsers;

    private final List<String> usersAvailable;

    private final LoginManager loginManager;
    private final StartManager startManager;
    private final CryptoActionManager cryptoActionManager;


    /**
     * Instantiates a new App controller.
     *
     * @param notifier the notifier
     * @param view     the view
     */
    public AppController(CryptocurrencyNotifier notifier, IDataView view) {
        this.notifier = notifier;
        this.view = view;
        this.notificationsWithUsers = new HashMap<>();
        this.loginManager = new LoginManager(this);
        this.cryptoActionManager = new CryptoActionManager(this);
        this.startManager = new StartManager(this);
        usersAvailable = Arrays.asList("USER_1", "USER_2", "USER_3", "USER_4", "USER_5");
    }

    /**
     * Gets notifier.
     *
     * @return the notifier
     */
    public CryptocurrencyNotifier getNotifier() {
        return notifier;
    }

    /**
     * Sets notifier.
     *
     * @param notifier the notifier
     */
    public void setNotifier(CryptocurrencyNotifier notifier) {
        this.notifier = notifier;
    }

    /**
     * Gets users available.
     *
     * @return the users available
     */
    public List<String> getUsersAvailable() {
        return usersAvailable;
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    public IDataView getView() {
        return view;
    }

    /**
     * Sets view.
     *
     * @param view the view
     */
    public void setView(IDataView view) {
        this.view = view;
    }

    /**
     * Manage actios from view.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "LOGIN" -> loginManager.handleLogin();
            case "START" -> startManager.handleStart();
            default -> cryptoActionManager.handleCryptoAction(e.getActionCommand());
        }
    }

    /**
     * Gets notifications with users.
     *
     * @return the notifications with users
     */
    public Map<IObserver, INotificationView> getNotificationsWithUsers() {
        return notificationsWithUsers;
    }
}
