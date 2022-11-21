package by.example.cashier.repository;

import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.common.AbstractRepositoryDTO;

import java.util.Optional;

/**
 * BankCard repository, CRUD
 */
public class BankCardRepositoryDTO extends AbstractRepositoryDTO<BankCardDto> {
    public BankCardRepositoryDTO() {
        super(ApplicationConfig.BANKCARD_CLASS_NAME);
    }

    public Optional<BankCardDto> getByCardNumber(String cardNumber) {
        return getAll().stream().filter(s -> s.getCardNumber().equals(cardNumber)).findAny();
    }
}

