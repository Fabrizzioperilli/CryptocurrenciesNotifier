package org.ull.dap.app.models.connections.csv;

/**
 * The type Crypto parser.
 */
public class CryptoParser extends Parser {

    /**
     * Instantiates a new Crypto parser.
     *
     * @param csvReader the csv reader
     */
    public CryptoParser(CSVReader csvReader) {
        super(csvReader);
    }

    /**
     * Convert object.
     *
     * @param value     the value
     * @param columName the colum name
     * @return the object
     */
    @Override
    protected Object convert(String value, String columName) {
        return value;
    }
}
