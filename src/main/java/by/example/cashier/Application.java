package by.example.cashier;

import by.example.cashier.command.CommandExecutor;
import by.example.cashier.command.Operation;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.UnlockCardThread;

import java.util.Locale;

public class Application {
    public static Locale locale;
    public static BankCardDto currentBankCard = null;

    public static void main(String[] args) {

        final ConsoleService consoleService = ApplicationConfig.getConsoleService();

        ApplicationConfig.initialize();

        locale = consoleService.getLocale();

        if (locale == null) System.exit(0);

        Thread unlockCardThread = new Thread(new UnlockCardThread(), "UnlockCardThread");
        unlockCardThread.start();

        Operation operation;
        do {
            operation = consoleService.askOperation();
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);

        unlockCardThread.interrupt();
    }
}
