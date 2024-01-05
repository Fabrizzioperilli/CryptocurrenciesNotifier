package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.views.IDataView;
import org.ull.dap.app.views.desktop.DataDesktop;

public class AppDesktop {
    public static void main(String[] args) {
        CryptocurrencyNotifier model = new CryptocurrencyNotifier();
        IDataView view = new DataDesktop(model);
        new AppController(model, view);
    }
}
