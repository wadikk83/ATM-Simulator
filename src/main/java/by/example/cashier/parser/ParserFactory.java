package by.example.cashier.parser;

import by.example.cashier.exсeption.FileFormatException;

public class ParserFactory {
    //private String fileFormatParser = PersistenceConfiguration.datasourceFileFormat;
    private String fileFormatParser = "TXT";


    public Parser getParser() {
        switch (fileFormatParser) {
            case "TXT":
                return new TxtParser();
            case "JSON":
                return new JsonParser();
            default:
                new FileFormatException("File format is not supported");
        }
        return null;
    }
}
