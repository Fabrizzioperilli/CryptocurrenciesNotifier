package org.ull.dap.app.views;

import java.util.List;
import java.util.Map;

public interface INotification {

    void showNotification(List<String> messages, Map<String, String> cryptoImages);
}
