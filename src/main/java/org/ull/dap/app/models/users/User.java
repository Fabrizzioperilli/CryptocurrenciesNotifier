package org.ull.dap.app.models.users;

import org.ull.dap.app.views.VentanaNotificacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User implements IObserver {

    private String name;
    private long id;
    private List<String> nameCryptos;
    private Map<String, Double> cryptoPrices;

    public User() {}

    public User(String name, long id, List<String> nameCryptos) {
        this.name = name;
        this.id = id;
        this.nameCryptos = nameCryptos;
        this.cryptoPrices = new HashMap<>();

        for (String nameCrypto : nameCryptos) {
            cryptoPrices.put(nameCrypto, -1.0);
        }
    }

    @Override
    public void update(String nameCrypto, double newPrice, VentanaNotificacion v) {
        cryptoPrices.put(nameCrypto, newPrice);
        System.out.println(name + " has been notified about " + nameCrypto +
                ", the price has changed to: " + cryptoPrices.get(nameCrypto) + " USD");
        v.crearNotificacion(name,nameCrypto,newPrice);
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
}
