package org.ull.dap.app.user;

import org.ull.dap.app.gui.VentanaNotificacion;

import java.util.List;

public interface IObserver {

    void update(String nameCrypto, double newPrice, VentanaNotificacion v);


    List<String> getNameCryptos();
}
