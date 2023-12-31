package org.ull.dap.app.models.connections.csv;

public class UsersParser extends Parser {

    public UsersParser(CSVReader csvReader) {
        super(csvReader);
    }
    @Override
    protected Object convert(String value, String columName) {
        return value;
    }
}
