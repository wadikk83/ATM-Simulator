package by.example.cashier.command.admin;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.ValidateService;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import static by.example.cashier.Application.locale;

public class AddCardCommand implements Command {

    private final ValidateService validateServiceImpl = ApplicationConfig.getValidateService();
    private final BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();
    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();
    private final ResourceBundle bundle = ResourceBundle.getBundle("add-card", locale);

    @Override
    public void execute() {

        consoleService.writeMessage("");
        consoleService.writeMessage(bundle.getString("general.message"));

        consoleService.writeMessage(bundle.getString("first.name"));
        String firstName = consoleService.readString();

        consoleService.writeMessage(bundle.getString("last.name"));
        String lastName = consoleService.readString();

        String cardNumber = validateServiceImpl.validateCardNumber();

        Integer pin = validateServiceImpl.validatePinNumber();

        consoleService.writeMessage(bundle.getString("balance.name"));
        Integer balance = validateServiceImpl.validatePositiveNumber();

        repository.save(new BankCardDto(firstName, lastName, cardNumber, pin, new BigDecimal(balance)));

        consoleService.writeMessage(bundle.getString("positive.message"));
        consoleService.writeMessage("===============================================================");
        consoleService.writeMessage("");
    }
}
