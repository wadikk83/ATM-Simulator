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

    ValidateService validateServiceImpl = ApplicationConfig.getValidateService();
    BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();

    private ResourceBundle bundle = ResourceBundle.getBundle("add-card", locale);

    @Override
    public void execute() {

        ConsoleService.writeMessage("");
        ConsoleService.writeMessage(bundle.getString("general.message"));

        ConsoleService.writeMessage(bundle.getString("first.name"));
        String firstName = ConsoleService.readString();

        ConsoleService.writeMessage(bundle.getString("last.name"));
        String lastName = ConsoleService.readString();

        //ConsoleService.writeMessage(bundle.getString("card.name"));
        String cardNumber = validateServiceImpl.validateCardNumber();

        //ConsoleService.writeMessage(bundle.getString("pin.name"));
        Integer pin = validateServiceImpl.validatePinNumber();

        ConsoleService.writeMessage(bundle.getString("balance.name"));
        Integer balance = validateServiceImpl.validatePositiveNumber();


        repository.save(new BankCardDto(firstName, lastName, cardNumber, pin, new BigDecimal(balance)));

        ConsoleService.writeMessage(bundle.getString("positive.message"));
        ConsoleService.writeMessage("===============================================================");
        ConsoleService.writeMessage("");
    }
}
