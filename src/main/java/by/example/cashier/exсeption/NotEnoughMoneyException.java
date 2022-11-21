package by.example.cashier.exсeption;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException() {
        super();
    }
}
