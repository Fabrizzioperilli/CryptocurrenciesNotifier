package org.ull.dap.app;

import org.ull.dap.app.gui.VentanaPrincipal;
import org.ull.dap.app.notifier.CryptocurrencyNotifier;
import org.ull.dap.app.notifier.Observable;
import org.ull.dap.app.user.IObserver;
import org.ull.dap.app.user.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Observable cryptocurrencyNotifier = new CryptocurrencyNotifier();
        VentanaPrincipal frame = new VentanaPrincipal(cryptocurrencyNotifier);
        frame.setVisible(true);
    }
}