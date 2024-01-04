package org.ull.dap.app.views;

import java.util.List;

/**
 * The interface View.
 */
public interface IView {

    /**
     * Gets users selected.
     *
     * @return the users selected
     */
    List<String> getUsersSelected();

    /**
     * Gets notifications.
     *
     * @return the notifications
     */
    List<INotification> getNotifications();

    /**
     * Window select cryptos.
     */
    void windowSelectCryptos();

    /**
     * Gets user combo box string.
     *
     * @return the user combo box string
     */
    String getUserComboBoxString();
}
