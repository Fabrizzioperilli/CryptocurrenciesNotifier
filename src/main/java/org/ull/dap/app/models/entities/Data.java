package org.ull.dap.app.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Data.
 */
public class Data {
    private String id;
    private int rank;
    private String symbol;
    private String name;
    private double supply;
    private double maxSupply;
    private double marketCapUsd;
    private double volumeUsd24Hr;
    private double priceUsd;
    private double changePercent24Hr;
    private double vwap24Hr;
    private String explorer;

    /**
     * Instantiates a new Data.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Data() {
    }

    /**
     * Instantiates a new Data.
     *
     * @param id                the id
     * @param rank              the rank
     * @param symbol            the symbol
     * @param name              the name
     * @param supply            the supply
     * @param maxSupply         the max supply
     * @param marketCapUsd      the market cap usd
     * @param volumeUsd24Hr     the volume usd 24 hr
     * @param priceUsd          the price usd
     * @param changePercent24Hr the change percent 24 hr
     * @param vwap24Hr          the vwap 24 hr
     * @param explorer          the explorer
     */
    public Data(String id, int rank, String symbol, String name, double supply, double maxSupply, double marketCapUsd, double volumeUsd24Hr, double priceUsd, double changePercent24Hr, double vwap24Hr, String explorer) {
        this.id = id;
        this.rank = rank;
        this.symbol = symbol;
        this.name = name;
        this.supply = supply;
        this.maxSupply = maxSupply;
        this.marketCapUsd = marketCapUsd;
        this.volumeUsd24Hr = volumeUsd24Hr;
        this.priceUsd = priceUsd;
        this.changePercent24Hr = changePercent24Hr;
        this.vwap24Hr = vwap24Hr;
        this.explorer = explorer;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets symbol.
     *
     * @param symbol the symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets supply.
     *
     * @return the supply
     */
    public double getSupply() {
        return supply;
    }

    /**
     * Sets supply.
     *
     * @param supply the supply
     */
    public void setSupply(double supply) {
        this.supply = supply;
    }

    /**
     * Gets max supply.
     *
     * @return the max supply
     */
    public double getMaxSupply() {
        return maxSupply;
    }

    /**
     * Sets max supply.
     *
     * @param maxSupply the max supply
     */
    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    /**
     * Gets market cap usd.
     *
     * @return the market cap usd
     */
    public double getMarketCapUsd() {
        return marketCapUsd;
    }

    /**
     * Sets market cap usd.
     *
     * @param marketCapUsd the market cap usd
     */
    public void setMarketCapUsd(double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    /**
     * Gets volume usd 24 hr.
     *
     * @return the volume usd 24 hr
     */
    public double getVolumeUsd24Hr() {
        return volumeUsd24Hr;
    }

    /**
     * Sets volume usd 24 hr.
     *
     * @param volumeUsd24Hr the volume usd 24 hr
     */
    public void setVolumeUsd24Hr(double volumeUsd24Hr) {
        this.volumeUsd24Hr = volumeUsd24Hr;
    }

    /**
     * Gets price usd.
     *
     * @return the price usd
     */
    public double getPriceUsd() {
        return priceUsd;
    }

    /**
     * Sets price usd.
     *
     * @param priceUsd the price usd
     */
    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    /**
     * Gets change percent 24 hr.
     *
     * @return the change percent 24 hr
     */
    public double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    /**
     * Sets change percent 24 hr.
     *
     * @param changePercent24Hr the change percent 24 hr
     */
    public void setChangePercent24Hr(double changePercent24Hr) {
        this.changePercent24Hr = changePercent24Hr;
    }

    /**
     * Gets vwap 24 hr.
     *
     * @return the vwap 24 hr
     */
    public double getVwap24Hr() {
        return vwap24Hr;
    }

    /**
     * Sets vwap 24 hr.
     *
     * @param vwap24Hr the vwap 24 hr
     */
    public void setVwap24Hr(double vwap24Hr) {
        this.vwap24Hr = vwap24Hr;
    }

    /**
     * Gets explorer.
     *
     * @return the explorer
     */
    public String getExplorer() {
        return explorer;
    }

    /**
     * Sets explorer.
     *
     * @param explorer the explorer
     */
    public void setExplorer(String explorer) {
        this.explorer = explorer;
    }

    public String toString() {
        return "{ \n" +
                "\tid= " + id + "\n" +
                "\trank= " + rank + "\n" +
                "\tsymbol= " + symbol + "\n" +
                "\tname= " + name + "\n" +
                "\tsupply= " + supply + "\n" +
                "\tmaxSupply= " + maxSupply + "\n" +
                "\tmarketCapUsd= " + marketCapUsd + "\n" +
                "\tvolumeUsd24Hr= " + volumeUsd24Hr + "\n" +
                "\tpriceUsd= " + priceUsd + "\n" +
                "\tchangePercent24Hr= " + changePercent24Hr + "\n" +
                "\tvwap24Hr= " + vwap24Hr + "\n" +
                "\texplorer= " + explorer + "\n  }";
    }
}
