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
    private final ResourceBundle bundle = ResourceBundle.getBundle("login", locale);
    private final ValidateServiceImpl validateServiceImpl = new ValidateServiceImpl();
    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();

    @Override
    public void execute() {
        consoleService.writeMessage(bundle.getString("general.message"));
        while (true) {
            String cardNumber = validateServiceImpl.validateCardNumber();
            BankCardDto bankCard;
            try {
                bankCard = bankCardService.getByCardNumber(cardNumber);
            } catch (EntityNotFoundException e) {
                consoleService.writeMessage(String.format(bundle.getString("not.found.message"), cardNumber));
                log.error("Bank card with number % not found", cardNumber);
                continue;
            }
            if (bankCard.isBlocked()) {
                consoleService.writeMessage(bundle.getString("blocked.message"));
                continue;
            }
            if (verificationPin(bankCard)) {
                currentBankCard = bankCard;
                consoleService.writeMessage(bundle.getString("verification.message"));
                break;
            }
        }
    }

    private Boolean verificationPin(BankCardDto bankCard) {
        int attempt = ApplicationConfig.MAX_NUMBER_OF_TRY_ENTER_PIN;
        while (attempt > 0) {
            consoleService.writeMessage(bundle.getString("pin.message"));
            Integer pin = validateServiceImpl.validatePinNumber();
            if (pin.compareTo(bankCard.getPinCode()) != 0) {
                attempt--;
                consoleService.writeMessage(bundle.getString("error.pin.message"));
                consoleService.writeMessage(bundle.getString("attempts.message") + attempt);
                continue;
            }
            return true;
        }
        if (attempt < 1) {
            bankCardService.blockCard(bankCard);
            consoleService.writeMessage(bundle.getString("blocked.message"));
            return false;
        }
        return true;
    }
}
