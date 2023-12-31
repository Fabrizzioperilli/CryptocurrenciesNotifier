package org.ull.dap.app.views.cli;

import org.ull.dap.app.views.INotification;

import java.util.List;

public class CLINotification implements INotification {

    public CLINotification() {

    }
    @Override
    public void createNotify(List<String> messages) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Notification: ");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("--------------------------------------------------------------------");
    }
}
