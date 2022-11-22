package by.example.cashier.command;

public enum Operation {
    MAIN_MENU("Main menu"),
    ADD_CARD("Add card"),
    DELETE_CARD("Delete card"),
    UPDATE_CARD("Update card"),
    VIEW_ALL_CARD("View all bank card"),
    LOGIN("Login"),
    INFO("Info"),
    CREDITING_MONEY("Crediting money"),
    WITHDRAW("Withdraw"),
    ADMIN_MENU("Admin menu"),
    EXIT("Exit");

    private String title;

    Operation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
