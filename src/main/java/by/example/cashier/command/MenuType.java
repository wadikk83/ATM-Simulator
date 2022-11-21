package by.example.cashier.command;

public enum MenuType {
    MAIN_MENU("Main menu"),
    ADMIN_MENU("Admin menu");

    private String title;

    MenuType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
