package by.example.cashier.command.admin;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ConsoleService;

import java.util.List;
import java.util.ResourceBundle;

import static by.example.cashier.Application.locale;

public class ViewAllCardCommand implements Command {

    private final BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();
    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();
    private final ResourceBundle bundle = ResourceBundle.getBundle("view-all-card", locale);

    @Override
    public void execute() {
        consoleService.writeMessage(bundle.getString("general.message"));

        final List<BankCardDto> allCard = repository.getAll();

        if (!allCard.isEmpty()) allCard.forEach(bankCard -> System.out.println(bankCard.toString()));
        else System.out.println(bundle.getString("empty.massage"));
    }
}
