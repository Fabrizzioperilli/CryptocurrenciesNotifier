package org.ull.dap.app.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Asset.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {
    private Data data;
    private String timestamp;

    /**
     * Instantiates a new Asset.
     */
    public Asset() {
    }

    /**
     * Instantiates a new Asset.
     *
     * @param data      the data
     * @param timestamp the timestamp
     */
    public Asset(Data data, String timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "Asset {\n" + "  Data= " + data + ", \n  timestamp=" + timestamp + "\n}";
    }
}
