package org.ull.dap.app.views;

import java.util.List;

public interface IView {

    List<String> getUsersSelected();

    List<INotification> getNotifications();

    void windowSelectCryptos();

    String getUserComboBoxString();
}
