package by.example.cashier.service;

import by.example.cashier.exсeption.CardNotFoundException;
import by.example.cashier.exсeption.DepositLimitException;
import by.example.cashier.exсeption.NotEnoughMoneyException;

public interface ATMService {

    boolean isAmountAvailable(int expectedAmount);

    void viewBalance() throws CardNotFoundException;

    void creditingMoney(Long amount) throws DepositLimitException, CardNotFoundException;

    Boolean WithdrawMoney(Long amount) throws NotEnoughMoneyException, CardNotFoundException;

    Boolean operationIsPossible();

}
