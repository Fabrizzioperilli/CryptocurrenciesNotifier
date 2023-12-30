package org.ull.dap.app.views;

import org.ull.dap.app.models.entities.Asset;

import java.util.List;

public interface INotification {

    void createNotify(List<String> messages);
}
