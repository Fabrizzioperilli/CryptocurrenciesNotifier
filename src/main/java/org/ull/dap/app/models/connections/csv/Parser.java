package org.ull.dap.app.models.connections.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Parser.
 */
public abstract class Parser {

    private final List<List<Object>> data;

    private final String[] header;

    /**
     * Instantiates a new Parser.
     *
     * @param csvReader the csv reader
     */
    public Parser(CSVReader csvReader) {
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

    /**
     * Convert object.
     *
     * @param value     the value
     * @param columName the colum name
     * @return the object
     */
    protected abstract Object convert(String value, String columName);

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<List<Object>> getData() {
        return data;
    }

    /**
     * Get header string [ ].
     *
     * @return the string [ ]
     */
    public String[] getHeader() {
        return header;
    }
}