package org.ull.dap.app.models.notifiers;

import org.ull.dap.app.models.connections.api.CryptocurrencyAPI;
import org.ull.dap.app.models.connections.api.IConnectionAPI;
import org.ull.dap.app.models.connections.csv.CSVReader;
import org.ull.dap.app.models.connections.csv.CryptoParser;
import org.ull.dap.app.models.connections.csv.Parser;
import org.ull.dap.app.models.entities.Asset;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;

import java.util.*;

public class CryptocurrencyNotifier implements Observable {

    private final List<IObserver> observers;

    private final IConnectionAPI connectionAPI;

    private final List<String> namesCryptocurrencies;

    private final List<Asset> assets;

    private final Parser cryptoParser;

    private static final String CSV_CRYPTOS_PATH = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTlyyYAafZXld0_zAcs2GoH1PKTJGnQoLX9hIitkZUXObyRQ_MWbHKyOOa_7u4SKm0bVkYt190jtn9S/pub?output=csv";

    private Map<String, String> cryptoNameImage;

    public CryptocurrencyNotifier() {
        this.observers = new ArrayList<>();
        this.connectionAPI = new CryptocurrencyAPI();
        this.cryptoParser = new CryptoParser(new CSVReader(CSV_CRYPTOS_PATH));
        this.namesCryptocurrencies = new ArrayList<>();
        this.cryptoNameImage = new HashMap<>();

        for (List<Object> row : cryptoParser.getData()) {
            this.namesCryptocurrencies.add(((String)row.get(0)).toLowerCase());
            this.cryptoNameImage.put((String)row.get(0), (String)row.get(1));
        }
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
                        observer.update(asset);
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

    public Map<String, String> getCryptoNameImage() {
        return cryptoNameImage;
    }
}
