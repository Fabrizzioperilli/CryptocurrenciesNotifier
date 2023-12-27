package org.ull.dap.app.views;

public interface IView {

    String[] getUsersSelected();

    void setUsersSelected(String[] usersSelected);

    void enableButtons(String name, boolean enable);
}
