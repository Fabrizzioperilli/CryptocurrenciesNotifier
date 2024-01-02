package org.ull.dap.app.models.connections.csv;

public class CryptoParser extends Parser{

    public CryptoParser(CSVReader csvReader) {
        super(csvReader);
    }

    @Override
    protected Object convert(String value, String columName) {
        return value;
    }
}
