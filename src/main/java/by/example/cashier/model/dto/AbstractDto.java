package by.example.cashier.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class AbstractDto implements Serializable {

    private Long id;

    LocalDateTime created;

    LocalDateTime updated;
}
