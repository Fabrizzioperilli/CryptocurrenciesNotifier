package org.ull.dap.app.models.connections.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Csv reader.
 */
public class CSVReader {

    private final String header;

    private final String url;

    private final List<String> records;

    /**
     * Instantiates a new Csv reader.
     *
     * @param url the url
     */
    public CSVReader(String url) {
        this.url = url;
        String[] data = HttpFileDownloader.downloadFromURL(url).split("\n");
        this.header = data[0];
        this.records = new ArrayList<>();
        records.addAll(Arrays.asList(data).subList(1, data.length));
    }

    /**
     * Gets header.
     *
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Gets records.
     *
     * @return the records
     */
    public List<String> getRecords() {
        return records;
    }

    /**
     * Partial print.
     *
     * @param row the row
     */
    public void partialPrint(int row) {
        System.out.println(header);
        int i = 0;
        for (String ignored : records) {
            System.out.println(records.get(i));
            i++;
            if (i == row) break;
        }
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

}