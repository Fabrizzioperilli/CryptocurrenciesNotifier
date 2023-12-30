package org.ull.dap.app.views;

import java.util.List;

public interface IView {

    String[] getUsersSelected();

    void setUsersSelected(String[] usersSelected);

    void enableButtons(String name, boolean enable);

    List<INotification> getNotifications();
}
