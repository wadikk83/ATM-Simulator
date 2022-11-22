package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.service.ATMService;
import by.example.cashier.service.ConsoleService;

import java.util.ResourceBundle;

import static by.example.cashier.Application.locale;

public class ExitCommand implements Command {

    private ResourceBundle bundle = ResourceBundle.getBundle("exit", locale);
    @Override
    public void execute() {
        ConsoleService.writeMessage(bundle.getString("exit.question.y.n"));
        String s = ConsoleService.readString();
        if (s.equalsIgnoreCase("y")) {
            ConsoleService.writeMessage(bundle.getString("thank.message"));

        }
    }
}
