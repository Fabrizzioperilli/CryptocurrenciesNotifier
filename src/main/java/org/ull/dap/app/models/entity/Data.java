package org.ull.dap.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public Data() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSupply() {
        return supply;
    }

    public void setSupply(double supply) {
        this.supply = supply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public double getVolumeUsd24Hr() {
        return volumeUsd24Hr;
    }

    public void setVolumeUsd24Hr(double volumeUsd24Hr) {
        this.volumeUsd24Hr = volumeUsd24Hr;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public void setChangePercent24Hr(double changePercent24Hr) {
        this.changePercent24Hr = changePercent24Hr;
    }

    public double getVwap24Hr() {
        return vwap24Hr;
    }

    public void setVwap24Hr(double vwap24Hr) {
        this.vwap24Hr = vwap24Hr;
    }

    public String getExplorer() {
        return explorer;
    }

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
