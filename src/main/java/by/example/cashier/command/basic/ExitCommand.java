package by.example.cashier.command.basic;

import by.example.cashier.command.Command;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.service.ConsoleService;

import java.util.ResourceBundle;

import static by.example.cashier.Application.locale;

public class ExitCommand implements Command {

    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();
    private final ResourceBundle bundle = ResourceBundle.getBundle("exit", locale);

    @Override
    public void execute() {
        consoleService.writeMessage(bundle.getString("exit.question.y.n"));
        String s = consoleService.readString();
        if ((s.equalsIgnoreCase("y") || (s.equalsIgnoreCase("д")))) {
            consoleService.writeMessage(bundle.getString("thank.message"));
        }
    }
}
