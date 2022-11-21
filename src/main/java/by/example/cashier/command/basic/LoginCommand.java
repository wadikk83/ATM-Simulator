package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.InterruptOperationException;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ConsoleService;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

import static by.example.cashier.Application.locale;

@Slf4j
public class LoginCommand implements Command {

    private final BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();
    private static final String REGEX_CARD_NUMBER = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";
    private ResourceBundle bundle = ResourceBundle.getBundle("login", locale);

    @Override
    public void execute() {
        ConsoleService.writeMessage(bundle.getString("before"));
        while (true) {
            String cardNumber = validateCardNumber();
            BankCardDto bankCard = null;
            /*try {
                bankCard = repository.getByCardNumber(cardNumber)
                        .orElseThrow(() -> new EntityNotFoundException("Bank card not found"));
            } catch (EntityNotFoundException e) {
                ConsoleService.writeMessage(String.format(bundle.getString("not.found.format"), cardNumber));
                log.error("Bank card with number % not found", cardNumber);
                continue;
            }*/
            break;
        }
    }

    public String validateCardNumber() {
        while (true) {
            ConsoleService.writeMessage(bundle.getString("specify.data.number"));
            String numberCard = null;
            numberCard = ConsoleService.readString();

            if (!numberCard.matches(REGEX_CARD_NUMBER)) {
                ConsoleService.writeMessage(bundle.getString("try.again.with.details"));
                continue;
            }

            return numberCard;
        }
    }
}
