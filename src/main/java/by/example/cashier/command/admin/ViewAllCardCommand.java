package by.example.cashier.command.admin;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.InterruptOperationException;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.ConsoleService;

import java.util.List;

public class ViewAllCardCommand implements Command {
    BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();

    @Override
    public void execute() {
        ConsoleService.writeMessage("===== List all card =====");

        final List<BankCardDto> allCard = repository.getAll();

        if (!allCard.isEmpty()) allCard.forEach(bankCard -> System.out.println(bankCard.toString()));
        else System.out.println("Bank card list is empty");

    }
}
