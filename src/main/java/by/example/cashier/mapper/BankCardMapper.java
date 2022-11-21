package by.example.cashier.mapper;

import by.example.cashier.model.dto.BankCardDto;

import java.math.BigDecimal;

public class BankCardMapper {

    public String toDTO(BankCardDto entity) {
        return entity.getFirstName() + " "
                + entity.getLastName() + ""
                + entity.getCardNumber() + ""
                + entity.getPinCode() + ""
                + entity.isBlocked() + ""
                + entity.getBalance() + ""
                + entity.getBlockingDateTime() + ""
                + entity.getCreated() + ""
                + entity.getUpdated();
    }

    public BankCardDto toEntity(String stringDto) {
        final String[] bankCard = stringDto.split(" ");
        return new BankCardDto(bankCard[0], bankCard[1], bankCard[2], Integer.parseInt(bankCard[3]), new BigDecimal(bankCard[4]));
    }
}
