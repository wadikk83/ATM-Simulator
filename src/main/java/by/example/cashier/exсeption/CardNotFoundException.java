package by.example.cashier.exсeption;

public class CardNotFoundException extends Exception {
    public CardNotFoundException() {
    }

    public CardNotFoundException(String message) {
        super(message);
    }
}
