package org.ull.dap.app.models.users;

import org.ull.dap.app.models.entities.Asset;

import java.util.*;

public class User implements IObserver {

    private String name;
    private long id;
    private List<String> nameCryptos;
    private Map<String, Double> cryptoPrices;

    private List<String> messagesToNotify;

    public User() {}

    public User(String name, long id, List<String> nameCryptos) {
        this.name = name;
        this.id = id;
        this.nameCryptos = nameCryptos;
        this.cryptoPrices = new HashMap<>();
        this.messagesToNotify = new ArrayList<>();

        for (String nameCrypto : nameCryptos) {
            cryptoPrices.put(nameCrypto, -1.0);
        }
    }

    @Override
    public void update(Asset asset) {
        String nameCrypto = asset.getData().getName();
        double newPrice = asset.getData().getPriceUsd();
        cryptoPrices.put(nameCrypto, newPrice);
        String messageNotify  = "  " + name + ", [" + nameCrypto + "] price has changed to: " + newPrice + " USD";
        messagesToNotify.add(messageNotify);
    }

    @Override
    public List<String> getNameCryptos() {
        return nameCryptos;
    }


    public Map<String, Double> getCryptoPrices() {
        return cryptoPrices;
    }

    @Override
    public void addCrypto(String name){
        nameCryptos.add(name);
    }

    @Override
    public void deleteCrypto(String name) {
        nameCryptos.remove(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String getName() {
        return name;
    }

    public List<String> getMessagesToNotify() {
        return messagesToNotify;
    }
}
