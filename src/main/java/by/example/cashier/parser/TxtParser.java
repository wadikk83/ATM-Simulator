package by.example.cashier.parser;

import by.example.cashier.model.dto.BankCardDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TxtParser<T> implements Parser<T> {
    @Override
    public void write(String className, List<T> list) {

        String fileName = className + ".txt";
        List<String> bk = list.stream()
                .map(e -> e.toString())
                .collect(Collectors.toList());

        try {
            Files.write(Paths.get(fileName), userNames);
        } catch (IOException e) {
            log.error(e.toString());
            System.out.println("Error writing data to file " + fileName);
        }
    }

    @Override
    public List<T> read(String fileName) {

        return (List<T>) new ArrayList<BankCardDto>();
    }
}
