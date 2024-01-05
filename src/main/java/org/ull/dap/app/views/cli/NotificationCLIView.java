package org.ull.dap.app.views.cli;

import org.ull.dap.app.views.INotificationView;

import java.util.List;
import java.util.Map;

/**
 * The type Notification cli.
 */
public class NotificationCLIView implements INotificationView {

    /**
     * Instantiates a new Notification cli.
     */
    public NotificationCLIView() {

    }

    /**
     * Show notification.
     *
     * @param messages     the messages
     * @param cryptoImages the crypto images
     */
    @Override
    public void showNotification(List<String> messages, Map<String, String> cryptoImages) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Notification: ");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("--------------------------------------------------------------------");
    }
}
