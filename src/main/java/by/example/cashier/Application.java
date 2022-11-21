package by.example.cashier;

import by.example.cashier.command.CommandExecutor;
import by.example.cashier.command.Operation;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.service.ConsoleService;

import java.util.Locale;

public class Application {
    public static Locale locale = new Locale("en", "US");

    public static void main(String[] args) {

        ApplicationConfig.initialize();

        Operation operation;
        do {
            operation = ConsoleService.askOperation();
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}
