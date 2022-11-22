package by.example.cashier.service;

import by.example.cashier.command.Command;
import by.example.cashier.command.CommandExecutor;
import by.example.cashier.command.MenuType;
import by.example.cashier.command.Operation;
import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.InterruptOperationException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Slf4j
public class ConsoleService {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param message receives a message and prints to the console
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints console menu
     *
     * @param allKnownCommandsMap gets the menu map
     */
    public static void printMenu(Map<Operation, Command> allKnownCommandsMap) {
        int iterator = 1;
        System.out.println("=== " + ApplicationConfig.START_MENU.getTitle() + " ===");
        for (Map.Entry<Operation, Command> entry : allKnownCommandsMap.entrySet()) {
            System.out.println(iterator + " -> " + entry.getKey().getTitle());
            iterator++;
        }
    }

    /**
     * @return a string from the console
     * @throws InterruptOperationException
     */
    public static String readString() {
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

    public static Operation askOperation() {
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

    /**
     * print bye-bye to console
     */
    public static void printExitMessage() {
        writeMessage("the.end");
    }

    public static void printOperationResult(Boolean result) {
        if (result) System.out.println("Operation was successful");
        else System.out.println("Operation failed");
    }
}
