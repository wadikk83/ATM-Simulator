package by.example.cashier.service;

import by.example.cashier.command.Command;
import by.example.cashier.command.Operation;

import java.util.Locale;
import java.util.Map;

public interface ConsoleService {
    void writeMessage(String message);

    void printMenu(Map<Operation, Command> allKnownCommandsMap);

    String readString();

    Operation askOperation();

    void printOperationResult(Boolean result);

    Locale getLocale();

}
