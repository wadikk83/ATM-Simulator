package by.example.cashier.service.impl;

import by.example.cashier.command.Command;
import by.example.cashier.command.CommandExecutor;
import by.example.cashier.command.MenuType;
import by.example.cashier.command.Operation;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.service.ConsoleService;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class ConsoleServiceImpl implements ConsoleService {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printMenu(Map<Operation, Command> allKnownCommandsMap) {
        int iterator = 1;
        System.out.println("=== " + ApplicationConfig.START_MENU.getTitle() + " ===");
        for (Map.Entry<Operation, Command> entry : allKnownCommandsMap.entrySet()) {
            System.out.println(iterator + " -> " + entry.getKey().getTitle());
            iterator++;
        }
    }

    @Override
    public String readString() {
        try {
            String string = bufferedReader.readLine();
            //if (string.toUpperCase().equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
            return string;
        } catch (IOException e) {
            System.out.println("IO error");
            log.error(e.toString());
        }
        return "";
    }

    @Override
    public Operation askOperation() {
        Operation operation;

        if (ApplicationConfig.START_MENU == MenuType.MAIN_MENU) printMenu(CommandExecutor.allKnownCommandsMap);
        else printMenu(CommandExecutor.allKnownAdminCommandsMap);

        writeMessage("Enter transaction number");
        while (true) {
            try {
                String input = readString();
                int n = Integer.parseInt(input);
                operation = CommandExecutor.getOperationByMenuIndex(n);
            } catch (IllegalArgumentException e) {
                writeMessage("Please enter a valid transaction number");
                continue;
            }
            break;
        }
        return operation;
    }

    @Override
    public void printOperationResult(Boolean result) {
        if (result) System.out.println("Operation was successful");
        else System.out.println("Operation failed");
    }

    @Override
    public Locale getLocale() {

        while (true) {
            writeMessage("Please enter -en- to select English interface or type 'exit' to exit");
            writeMessage("Пожалуйста введите -ru- для выбора английского интерфейса или 'exit' для выхода");
            switch (readString()) {
                case "en":
                    return new Locale("en", "US");
                case "ru":
                    return new Locale("ru", "RU");
                case "exit":
                    return null;
                default:
                    writeMessage("Error! Please try again");
            }
        }
    }
}
