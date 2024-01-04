package org.ull.dap.app.models.users;

import org.ull.dap.app.models.entities.Asset;

import java.util.List;

/**
 * The interface Observer.
 */
public interface IObserver {

    /**
     * Update.
     *
     * @param asset the asset
     */
    void update(Asset asset);

    /**
     * Add crypto.
     *
     * @param name the name
     */
    void addCrypto(String name);

    /**
     * Delete crypto.
     *
     * @param name the name
     */
    void deleteCrypto(String name);

    /**
     * Gets name cryptos.
     *
     * @return the name cryptos
     */
    List<String> getNameCryptos();

    /**
     * Gets messages to notify.
     *
     * @return the messages to notify
     */
    List<String> getMessagesToNotify();

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();
}
