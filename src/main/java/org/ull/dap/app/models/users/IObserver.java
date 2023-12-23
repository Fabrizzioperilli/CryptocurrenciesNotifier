package org.ull.dap.app.models.users;

import org.ull.dap.app.views.VentanaNotificacion;

import java.util.List;

public interface IObserver {

    void update(String nameCrypto, double newPrice, VentanaNotificacion v);
    void addCrypto(String name);
    void deleteCrypto(String name);


    List<String> getNameCryptos();
}
