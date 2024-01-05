package org.ull.dap.app.views;

import java.util.List;

/**
 * The interface View.
 */
public interface IDataView {

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
    List<INotificationView> getNotifications();

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
