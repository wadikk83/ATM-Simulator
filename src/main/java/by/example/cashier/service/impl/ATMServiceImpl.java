package by.example.cashier.service.impl;

import by.example.cashier.exсeption.CardNotFoundException;
import by.example.cashier.exсeption.DepositLimitException;
import by.example.cashier.exсeption.NotEnoughMoneyException;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.service.ATMService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Data
@Slf4j
public class ATMServiceImpl implements ATMService {

    private static final int MAX_AMOUNT = 1_000_000;

    private BigDecimal balanceATM;

    private BankCardDto bankCard;

    public ATMServiceImpl(BigDecimal balanceATM, BankCardDto bankCard) {
        this.balanceATM = balanceATM;
        this.bankCard = bankCard;
    }

    @Override
    public boolean isAmountAvailable(int expectedAmount) {
        return BigDecimal.valueOf(expectedAmount).compareTo(balanceATM) >= 0;
    }

    @Override
    public void viewBalance() throws CardNotFoundException {
        if (!operationIsPossible()) throw new CardNotFoundException();
        else System.out.println("Your balance -> " + bankCard.getBalance());
    }

    @Override
    public void creditingMoney(Long amount) throws DepositLimitException, CardNotFoundException {
        if (!operationIsPossible()) throw new CardNotFoundException();

        if (amount > MAX_AMOUNT) {
            throw new DepositLimitException("The amount of the transaction credited to the account should not exceed " + MAX_AMOUNT);
        }
        bankCard.setBalance(bankCard.getBalance().add(BigDecimal.valueOf(amount)));
        System.out.println("Сredited: " + amount);
    }

    @Override
    public Boolean WithdrawMoney(Long amount) throws NotEnoughMoneyException, CardNotFoundException {
        if (!operationIsPossible()) throw new CardNotFoundException();

        if (BigDecimal.valueOf(amount).compareTo(bankCard.getBalance()) >= 0) {
            throw new NotEnoughMoneyException("No enough money on the account");
        }

        if (BigDecimal.valueOf(amount).compareTo(balanceATM) >= 0) {
            throw new NotEnoughMoneyException("No enough money on the atm");
        }

        bankCard.setBalance(bankCard.getBalance().subtract(BigDecimal.valueOf(amount)));
        System.out.println("Withdrawn from account -> " + amount);
        return true;
    }

    @Override
    public Boolean operationIsPossible() {
        return bankCard != null;
    }
}
