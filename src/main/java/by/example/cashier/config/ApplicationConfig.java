package by.example.cashier.config;

import by.example.cashier.command.MenuType;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ValidateService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {

    public static String fileDirectory;
    public static String BANKCARD_CLASS_NAME;

    public static MenuType START_MENU;

    public static void initialize() {

        //TODO обработать ошибки
        Properties property = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileDirectory = property.getProperty("file.path");

        BANKCARD_CLASS_NAME = fileDirectory + "BankCardDao";
        START_MENU = MenuType.MAIN_MENU;
    }

    public static BankCardRepositoryDTO getBankCardRepository() {
        return new BankCardRepositoryDTO();
    }

    public static ValidateService getValidateService() {
        return new ValidateService();
    }
}
