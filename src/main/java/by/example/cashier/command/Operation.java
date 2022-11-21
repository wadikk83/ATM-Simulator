package by.example.cashier.command;

public enum Operation {
    MAIN_MENU("Main menu"),
    ADD_CARD("Add card"),
    DELETE_CARD("Delete card"),
    UPDATE_CARD("Update card"),
    VIEW_ALL_CARD("View all bank card"),
    LOGIN("Login"),
    INFO("Info"),
    DEPOSIT("Deposit"),
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


    /*public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException {
        Operation[] ops = values();
        if (i > ops.length - 1 || i < 1) throw new IllegalArgumentException();
        return ops[i];
    }*/
}
