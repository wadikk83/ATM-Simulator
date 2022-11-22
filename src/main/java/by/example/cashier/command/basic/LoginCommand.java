package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.EntityNotFoundException;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.service.BankCardService;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.impl.ValidateServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

import static by.example.cashier.Application.currentBankCard;
import static by.example.cashier.Application.locale;

@Slf4j
public class LoginCommand implements Command {

    private final BankCardService bankCardService = ApplicationConfig.getBankCardService();
    private final int MAX_NUMBER_OF_TRY_ENTER_PIN = 3;
    private final ResourceBundle bundle = ResourceBundle.getBundle("login", locale);
    private final ValidateServiceImpl validateServiceImpl = new ValidateServiceImpl();

    @Override
    public void execute() {
        ConsoleService.writeMessage(bundle.getString("before"));
        while (true) {
            String cardNumber = validateServiceImpl.validateCardNumber();
            BankCardDto bankCard;
            try {
                bankCard = bankCardService.getByCardNumber(cardNumber);
            } catch (EntityNotFoundException e) {
                ConsoleService.writeMessage(String.format(bundle.getString("not.found.format"), cardNumber));
                log.error("Bank card with number % not found", cardNumber);
                continue;
            }
            if (bankCard.isBlocked()) {
                ConsoleService.writeMessage("Your card is blocked");
                continue;
            }

            if (verificationPin(bankCard)) {
                //atm.setBankCard(bankCard);
                currentBankCard = bankCard;
                ConsoleService.writeMessage("Verification passed successfully");
                break;
            }
        }
    }

    private Boolean verificationPin(BankCardDto bankCard) {
        int attempt = MAX_NUMBER_OF_TRY_ENTER_PIN;
        while (attempt > 0) {
            ConsoleService.writeMessage(bundle.getString("specify.data.pin"));
            Integer pin = validateServiceImpl.validatePinNumber();
            if (pin.compareTo(bankCard.getPinCode()) != 0) {
                attempt--;
                ConsoleService.writeMessage("Incorrect pin");
                ConsoleService.writeMessage(attempt + " tries left");
                continue;
            }
            return true;
        }
        if (attempt < 1) {
            bankCardService.blockCard(bankCard);
            ConsoleService.writeMessage("Your card is blocked for a day");
            return false;
        }
        return true;
    }
}
