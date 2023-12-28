package org.ull.dap.app.models.notifiers;

import org.ull.dap.app.models.connections.CryptocurrencyAPI;
import org.ull.dap.app.models.connections.IConnectionAPI;
import org.ull.dap.app.models.entities.Asset;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CryptocurrencyNotifier implements Observable {

    private final List<IObserver> observers;

    private final IConnectionAPI connectionAPI;

    private final List<String> namesCryptocurrencies;

    private final List<Asset> assets;

    private final int TIME_TO_NOTIFY = 40;

    public CryptocurrencyNotifier() {
        this.observers = new ArrayList<>();
        this.connectionAPI = new CryptocurrencyAPI();
        this.namesCryptocurrencies = List.of("bitcoin", "ethereum", "litecoin", "cardano");
        this.assets = new ArrayList<>();
    }

    @Override
    public void subscribe(IObserver observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(IObserver observer) {
        this.observers.remove(observer);
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            Map<String, Double> cryptoPrices = ((User) observer).getCryptoPrices();
            for (Asset asset : assets) {
                String assetId = asset.getData().getId();

                if (observer.getNameCryptos().contains(assetId)) {
                    Double previousPrice = cryptoPrices.getOrDefault(assetId, 0.0);
                    Double currentPrice = asset.getData().getPriceUsd();

                    if (!Objects.equals(previousPrice, currentPrice)) {
                        observer.update(assetId, currentPrice);
                        cryptoPrices.put(assetId, currentPrice);
                    }
                }
            }
        }
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public List<String> getNamesCryptocurrencies() {
        return namesCryptocurrencies;
    }

    public IConnectionAPI getConnectionAPI() {
        return connectionAPI;
    }
}
