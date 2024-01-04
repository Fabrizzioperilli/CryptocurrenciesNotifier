package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.desktop.ViewDesktop;

public class AppDesktop {
    public static void main(String[] args) {
        CryptocurrencyNotifier model = new CryptocurrencyNotifier();
        IView view = new ViewDesktop(model);
        new AppController(model, view);
    }
}
