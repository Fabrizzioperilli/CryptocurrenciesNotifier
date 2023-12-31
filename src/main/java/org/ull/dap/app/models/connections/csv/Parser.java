package org.ull.dap.app.models.connections.csv;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {
    private CSVReader csvReader;

    private List<List<Object>> data;

    private String[] header;

    public Parser(CSVReader csvReader) {
        this.csvReader = csvReader;
        data = new ArrayList<>();

        this.header = csvReader.getHeader().split(",");

        // Recorrer los registros del CSV
        for (String record : csvReader.getRecords()) {
            List<Object> row = new ArrayList<>();
            String[] fields = record.split(",");

            for (int i = 0; i < fields.length; i++) {
                Object value = convert(fields[i], header[i]);
                row.add(value);
            }
            data.add(row);
        }
    }

    protected abstract Object convert(String value, String columName);

    public List<List<Object>> getData() {
        return data;
    }

    public String[] getHeader() {
        return header;
    }
}