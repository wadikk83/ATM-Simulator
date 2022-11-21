package by.example.cashier.config;

import by.example.cashier.command.MenuType;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ValidateService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ApplicationConfig {

    public static String fileDirectory;

    public static String fileFormatParser;
    public static String BANKCARD_CLASS_NAME;

    public static MenuType START_MENU;

    public static void initialize() {

        Properties prop = new Properties();
        try {
            prop.load(ClassLoader.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileDirectory = prop.getProperty("file.path");
        fileFormatParser = prop.getProperty("fileFormatParser");

        BANKCARD_CLASS_NAME = fileDirectory +"BankCardDao";
        START_MENU = MenuType.MAIN_MENU;
    }

    public static BankCardRepositoryDTO getBankCardRepository() {
        return new BankCardRepositoryDTO();
    }

    public static ValidateService getValidateService() {
        return new ValidateService();
    }
}
