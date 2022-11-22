package by.example.cashier.service.impl;

import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.exсeption.EntityNotFoundException;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import by.example.cashier.service.BankCardService;

import java.time.LocalDateTime;

public class BankCardServiceImpl implements BankCardService {
    private final BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();

    @Override
    public BankCardDto getByCardNumber(String cardNumber) throws EntityNotFoundException {
        return repository.getByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Bank card not found"));
    }

    @Override
    public void blockCard(BankCardDto bankCard) {
        final BankCardDto bankCardDto = repository.getById(bankCard.getId()).get();
        bankCardDto.setBlocked(true);
        bankCardDto.setBlockingDateTime(LocalDateTime.now());
        repository.update(bankCardDto);

    }
}
