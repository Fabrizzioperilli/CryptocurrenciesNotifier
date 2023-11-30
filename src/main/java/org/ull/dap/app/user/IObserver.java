package org.ull.dap.app.user;

import java.util.List;

public interface IObserver {

    void update(String nameCrypto, double newPrice);

    List<String> getNameCryptos();
}
