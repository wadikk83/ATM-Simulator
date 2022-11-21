package by.example.cashier.command.admin;

import by.example.cashier.command.Command;
import by.example.cashier.command.MenuType;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.InterruptOperationException;

public class AdminMenuCommand implements Command {
    @Override
    public void execute() {
        ApplicationConfig.START_MENU = MenuType.ADMIN_MENU;
    }
}
