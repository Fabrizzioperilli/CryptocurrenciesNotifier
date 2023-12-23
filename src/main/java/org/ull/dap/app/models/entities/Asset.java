package org.ull.dap.app.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {
    private Data data;
    private String timestamp;

    public Asset() {
    }

    public Asset(Data data, String timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "Asset {\n" + "  Data= " + data + ", \n  timestamp=" + timestamp + "\n}";
    }
}
