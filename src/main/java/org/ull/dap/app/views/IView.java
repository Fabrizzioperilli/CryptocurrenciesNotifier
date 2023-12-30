package org.ull.dap.app.views;

import java.util.List;

public interface IView {

    List<String> getUsersSelected();

    void setUsersSelected(List<String> usersSelected);

    void enableButtons(String name, boolean enable);

    List<INotification> getNotifications();
}
