package org.ull.dap.app.models.users;

import org.ull.dap.app.models.entities.Asset;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The type User.
 */
public class User implements IObserver {

    private String name;
    private long id;
    private List<String> nameCryptos;
    private Map<String, Double> cryptoPrices;
    private List<String> messagesToNotify;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name        the name
     * @param id          the id
     * @param nameCryptos the name cryptos
     */
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
        String nameCrypto = asset.getData().getId();
        double newPrice = asset.getData().getPriceUsd();
        double percentageChange = Math.round(asset.getData().getChangePercent24Hr() * 1000.0) / 1000.0;
        cryptoPrices.put(nameCrypto, newPrice);

        LocalDateTime dateTime = LocalDateTime.now();
        String timeAndDate = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String messageNotify = "  " + name + " [" + nameCrypto + "].  " +  timeAndDate +
                "\n  New price:   " + newPrice + " USD\n" +
                "  Change percentage 24h:   " + percentageChange + "%\n";

        messagesToNotify.add(messageNotify);
    }

    @Override
    public List<String> getNameCryptos() {
        return nameCryptos;
    }


    /**
     * Gets crypto prices.
     *
     * @return the crypto prices
     */
    public Map<String, Double> getCryptoPrices() {
        return cryptoPrices;
    }

    @Override
    public void addCrypto(String name) {
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

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets name cryptos.
     *
     * @param nameCryptos the name cryptos
     */
    public void setNameCryptos(List<String> nameCryptos) {
        this.nameCryptos = nameCryptos;
    }

    /**
     * Sets crypto prices.
     *
     * @param cryptoPrices the crypto prices
     */
    public void setCryptoPrices(Map<String, Double> cryptoPrices) {
        this.cryptoPrices = cryptoPrices;
    }

    /**
     * Sets messages to notify.
     *
     * @param messagesToNotify the messages to notify
     */
    public void setMessagesToNotify(List<String> messagesToNotify) {
        this.messagesToNotify = messagesToNotify;
    }
}
