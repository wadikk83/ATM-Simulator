package by.example.cashier.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Slf4j
public class BankCardDto extends AbstractDto {

    private String firstName;
    private String lastName;
    private final String cardNumber;
    private Integer pinCode;
    private boolean isBlocked;

    private BigDecimal balance;

    private LocalDateTime blockingDateTime;

    public BankCardDto(String firstName, String lastName, String cardNumber, Integer pinCode, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
        blockingDateTime = LocalDateTime.now();
        log.warn("Bank card with number " + cardNumber + " has been blocked at time " + blockingDateTime);
    }

}
