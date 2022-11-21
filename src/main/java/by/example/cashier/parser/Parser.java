package by.example.cashier.parser;

import by.example.cashier.model.dto.BankCardDto;

import java.util.List;

/*public interface Parser {

    void write(String filename, List<BankCardDto> list);

    List<BankCardDto> read(String fileName);

}*/
public interface Parser<T> {

    void write(String className, List<T> list);

    List<T> read(String fileName);

}