package org.ull.dap.app.views.cli;

import org.ull.dap.app.views.INotification;

import java.util.List;
import java.util.Map;

public class NotificationCLI implements INotification {

    public NotificationCLI() {

    }
    @Override
    public void showNotification(List<String> messages, Map<String,String> cryptoImages) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Notification: ");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("--------------------------------------------------------------------");
    }
}
