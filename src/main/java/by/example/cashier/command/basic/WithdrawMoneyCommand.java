package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.CardNotFoundException;
import by.example.cashier.exсeption.NotEnoughMoneyException;
import by.example.cashier.service.ATMService;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.ValidateService;

import static by.example.cashier.Application.currentBankCard;

public class WithdrawMoneyCommand implements Command {
    @Override
    public void execute() {
        ATMService atmService = ApplicationConfig.getATMService(currentBankCard);
        ValidateService validateService = ApplicationConfig.getValidateService();

        if (atmService.operationIsPossible()) {
            ConsoleService.writeMessage("Withdrawal from card balance");
            ConsoleService.writeMessage("Please enter the amount");
            Integer amount = validateService.validatePositiveNumber();

            final Boolean operationResult;
            try {
                operationResult = atmService.WithdrawMoney(amount.longValue());
                ConsoleService.printOperationResult(operationResult);
            } catch (NotEnoughMoneyException e) {
                ConsoleService.writeMessage(e.getMessage());
            } catch (CardNotFoundException e) {
                ConsoleService.writeMessage("Bank card is not register");
            }

        } else {
            ConsoleService.writeMessage("Bank card is not register");
        }
    }
}
