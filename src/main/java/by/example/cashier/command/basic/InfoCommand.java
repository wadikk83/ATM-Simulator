package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.CardNotFoundException;
import by.example.cashier.service.ATMService;
import by.example.cashier.service.ConsoleService;

import java.util.ResourceBundle;

import static by.example.cashier.Application.currentBankCard;
import static by.example.cashier.Application.locale;

public class InfoCommand implements Command {

    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();
    private final ResourceBundle bundle = ResourceBundle.getBundle("info", locale);

    @Override
    public void execute() {
        ATMService atmService = ApplicationConfig.getATMService(currentBankCard);
        try {
            atmService.viewBalance();
        } catch (CardNotFoundException e) {
            consoleService.writeMessage(bundle.getString("error.message"));
        }
    }
}
