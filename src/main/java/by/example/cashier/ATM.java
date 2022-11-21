package by.example.cashier;

public class ATM {

    private static final int MAX_AMOUNT = 1_000_000;

    private Long balanceATM;

    public ATM(Long balanceATM) {
        this.balanceATM = balanceATM;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return balanceATM >= expectedAmount;
    }

}
