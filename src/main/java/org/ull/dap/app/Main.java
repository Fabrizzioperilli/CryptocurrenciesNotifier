package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.MainView;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.notifiers.Observable;

public class Main {
    public static void main(String[] args) {
        Observable cryptocurrencyNotifier = new CryptocurrencyNotifier();
        IView frame = new MainView(cryptocurrencyNotifier);
        AppController appController = new AppController(cryptocurrencyNotifier, frame);
    }
}