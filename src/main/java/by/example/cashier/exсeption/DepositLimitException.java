package by.example.cashier.exсeption;

public class DepositLimitException extends Exception{
    public DepositLimitException(String message) {
        super(message);
    }
}
