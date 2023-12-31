package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.cli.CLIView;
import org.ull.dap.app.views.desktop.DesktopView;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;

public class Main {
    public static void main(String[] args) {
        CryptocurrencyNotifier cryptocurrencyNotifier = new CryptocurrencyNotifier();
        IView frame = new DesktopView(cryptocurrencyNotifier);
//        IView frame  = new CLIView(cryptocurrencyNotifier);
        new AppController(cryptocurrencyNotifier, frame);
    }
}