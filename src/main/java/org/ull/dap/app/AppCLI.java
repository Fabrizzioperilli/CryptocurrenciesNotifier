package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.cli.ViewCLI;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;

public class AppCLI {
    public static void main(String[] args) {
        CryptocurrencyNotifier model = new CryptocurrencyNotifier();
        IView view = new ViewCLI(model);
        new AppController(model, view);
    }
}