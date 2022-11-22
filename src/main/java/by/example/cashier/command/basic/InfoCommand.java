package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.CardNotFoundException;
import by.example.cashier.service.ATMService;
import by.example.cashier.service.ConsoleService;

import static by.example.cashier.Application.currentBankCard;

public class InfoCommand implements Command {

    @Override
    public void execute() {
        ATMService atmService = ApplicationConfig.getATMService(currentBankCard);
        try {
            atmService.viewBalance();
        } catch (CardNotFoundException e) {
            ConsoleService.writeMessage("Bank card is not register");
        }
    }
}
