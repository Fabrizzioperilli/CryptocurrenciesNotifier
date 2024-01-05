package org.ull.dap.app.views;

import java.util.List;
import java.util.Map;

/**
 * The interface Notification.
 */
public interface INotificationView {

    /**
     * Show notification.
     *
     * @param messages     the messages
     * @param cryptoImages the crypto images
     */
    void showNotification(List<String> messages, Map<String, String> cryptoImages);
}
