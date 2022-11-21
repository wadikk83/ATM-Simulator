package by.example.cashier.mapper;

import by.example.cashier.model.dto.BankCardDto;

import java.util.Arrays;
import java.util.Objects;

public class BankCardMapper {

    public String toDTO(BankCardDto entity) {
        return Objects.isNull(entity)
                ? null
                : entity.toString();


        /*firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", pinCode=" + pinCode +
                ", isBlocked=" + isBlocked +
                ", balance=" + balance +
                ", blockingDateTime=" + blockingDateTime +
                ", created=" + created +
                ", updated=" + updated +
        */

    }

    public BankCardDto toEntity(String stringDto) {
        Arrays.stream(stringDto.split(" "))
                .map(bankCard -> new BankCardDto(
                        bankCard[0],
                        bankCard[1],
                        bankCard[2],
                        bankCard[3],
                        bankCard[4]
                        ))

    }
}
