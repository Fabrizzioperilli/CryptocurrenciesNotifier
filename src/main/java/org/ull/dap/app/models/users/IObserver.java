package org.ull.dap.app.models.users;

import java.util.List;

public interface IObserver {

    void update(String nameCrypto, double newPrice);
    void addCrypto(String name);
    void deleteCrypto(String name);
    List<String> getNameCryptos();
}
