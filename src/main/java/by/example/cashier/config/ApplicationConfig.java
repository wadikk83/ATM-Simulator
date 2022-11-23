package by.example.cashier.config;

import by.example.cashier.command.MenuType;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ATMService;
import by.example.cashier.service.BankCardService;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.ValidateService;
import by.example.cashier.service.impl.ATMServiceImpl;
import by.example.cashier.service.impl.BankCardServiceImpl;
import by.example.cashier.service.impl.ConsoleServiceImpl;
import by.example.cashier.service.impl.ValidateServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class ApplicationConfig {

    public static String fileDirectory;

    public static String fileFormatParser;
    public static String BANKCARD_CLASS_NAME;

    public static Integer MAX_NUMBER_OF_TRY_ENTER_PIN;

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
        MAX_NUMBER_OF_TRY_ENTER_PIN = Integer.valueOf(prop.getProperty("MAX_NUMBER_OF_TRY_ENTER_PIN"));

        BANKCARD_CLASS_NAME = fileDirectory + "BankCardDao";
        START_MENU = MenuType.MAIN_MENU;
    }

    public static BankCardRepositoryDTO getBankCardRepository() {
        return new BankCardRepositoryDTO();
    }

    public static BankCardService getBankCardService() {
        return new BankCardServiceImpl();
    }

    public static ValidateService getValidateService() {
        return new ValidateServiceImpl();
    }

    public static ATMService getATMService(BankCardDto bankCard) {
        return new ATMServiceImpl(BigDecimal.valueOf(1_000_000_000), bankCard);
    }

    public static ConsoleService getConsoleService() {
        return new ConsoleServiceImpl();
    }
}
