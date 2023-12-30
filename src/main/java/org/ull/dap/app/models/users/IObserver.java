package org.ull.dap.app.models.users;

import org.ull.dap.app.models.entities.Asset;

import java.util.List;

public interface IObserver {

    void update(Asset asset);
    void addCrypto(String name);
    void deleteCrypto(String name);
    List<String> getNameCryptos();

    List<String> getMessagesToNotify();

    String getName();
}
