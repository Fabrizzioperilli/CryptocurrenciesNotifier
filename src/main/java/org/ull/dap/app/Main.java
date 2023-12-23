package org.ull.dap.app;

import org.ull.dap.app.views.VentanaPrincipal;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.notifiers.Observable;

public class Main {
    public static void main(String[] args) {
        Observable cryptocurrencyNotifier = new CryptocurrencyNotifier();
        VentanaPrincipal frame = new VentanaPrincipal(cryptocurrencyNotifier);
        frame.setVisible(true);
    }
}