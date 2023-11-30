package org.ull.dap.app.user;

import org.ull.dap.app.gui.VentanaNotificacion;

import java.util.List;

public interface IObserver {

    void update(String nameCrypto, double newPrice, VentanaNotificacion v);
    void addCrypto(String name);
    void deleteCrypto(String name);


    List<String> getNameCryptos();
}
