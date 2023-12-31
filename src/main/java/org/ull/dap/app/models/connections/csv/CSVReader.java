package org.ull.dap.app.models.connections.csv;

import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private String header;

    private String url;

    private List<String> records;

    public CSVReader(String url) {
        this.url = url;
        String[] data = HttpFileDownloader.downloadFromURL(url).split("\n");
        this.header = data[0];
        this.records = new ArrayList<>();

        for (int i = 1; i < data.length; i++)
            records.add(data[i]);
    }

    public String getHeader() {
        return header;
    }

    public List<String> getRecords() {
        return records;
    }

    public void partialPrint(int row) {
        System.out.println(header);
        int i = 0;
        for (String s : records) {
            System.out.println(records.get(i));
            i++;
            if (i == row) break;
        }
    }

    public String getUrl() {
        return url;
    }

}