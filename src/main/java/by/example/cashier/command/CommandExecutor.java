package by.example.cashier.command;

import by.example.cashier.command.admin.AddCardCommand;
import by.example.cashier.command.admin.AdminMenuCommand;
import by.example.cashier.command.admin.ViewAllCardCommand;
import by.example.cashier.command.basic.*;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.InterruptOperationException;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandExecutor {

    public final static Map<Operation, Command> allKnownCommandsMap;
    public final static Map<Operation, Command> allKnownAdminCommandsMap;

    public CommandExecutor() {
    }

    static {
        allKnownCommandsMap = new LinkedHashMap<>();
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.ADMIN_MENU, new AdminMenuCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());

        allKnownAdminCommandsMap = new LinkedHashMap<>();
        allKnownAdminCommandsMap.put(Operation.ADD_CARD, new AddCardCommand());
        allKnownAdminCommandsMap.put(Operation.VIEW_ALL_CARD, new ViewAllCardCommand());
        allKnownAdminCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final Operation getOperationByMenuIndex(int index) {
        Object[] ops;
        if (ApplicationConfig.START_MENU == MenuType.MAIN_MENU) {
            ops = allKnownCommandsMap.keySet().toArray();
        } else {
            ops = allKnownAdminCommandsMap.keySet().toArray();

        }
        if (index > ops.length || index < 1) throw new IllegalArgumentException();
        else return (Operation) ops[index - 1];
    }

    public static final void execute(Operation operation) {
        if (ApplicationConfig.START_MENU == MenuType.MAIN_MENU) allKnownCommandsMap.get(operation).execute();
        else allKnownAdminCommandsMap.get(operation).execute();
    }
}
