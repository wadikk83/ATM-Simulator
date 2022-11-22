package by.example.cashier.service;

import by.example.cashier.exсeption.EntityNotFoundException;
import by.example.cashier.model.dto.BankCardDto;

public interface BankCardService {

    BankCardDto getByCardNumber(String cardNumber) throws EntityNotFoundException;

    void blockCard(BankCardDto bankCard);
}
