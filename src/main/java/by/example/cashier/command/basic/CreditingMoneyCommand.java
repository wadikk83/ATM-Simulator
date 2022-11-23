package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.CardNotFoundException;
import by.example.cashier.exсeption.DepositLimitException;
import by.example.cashier.service.ATMService;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.ValidateService;

import java.util.ResourceBundle;

import static by.example.cashier.Application.currentBankCard;
import static by.example.cashier.Application.locale;

public class CreditingMoneyCommand implements Command {

    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();
    private final ValidateService validateService = ApplicationConfig.getValidateService();
    private final ResourceBundle bundle = ResourceBundle.getBundle("crediting-money", locale);

    @Override
    public void execute() {
        ATMService atmService = ApplicationConfig.getATMService(currentBankCard);

        if (atmService.operationIsPossible()) {
            consoleService.writeMessage(bundle.getString("general.message"));
            consoleService.writeMessage(bundle.getString("general2.message"));
            Integer amount = validateService.validatePositiveNumber();
            try {
                atmService.creditingMoney(amount.longValue());
            } catch (DepositLimitException e) {
                consoleService.writeMessage(e.toString());
            } catch (CardNotFoundException e) {
                consoleService.writeMessage(bundle.getString("error.message"));
            }
        } else {
            consoleService.writeMessage(bundle.getString("error.message"));
        }
    }
}
