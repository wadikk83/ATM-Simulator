package by.example.cashier.parser;

import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.service.ConsoleService;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TxtParser implements Parser<BankCardDto> {

    @Override
    public void write(String className, List<BankCardDto> list) {
        String fileName = className + ".txt";

        List<String> listToSave = list.stream()
                .map(e -> e.toString())
                .collect(Collectors.toList());
        try {

            Files.write(Paths.get(fileName), listToSave, StandardOpenOption.CREATE);
        } catch (IOException e) {
            log.error(e.toString());
            System.out.println("Error writing data to file " + fileName);
        }
    }

    @Override
    public List<BankCardDto> read(String fileName) {
        fileName += ".txt";

        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                ConsoleService.writeMessage("Error creating file");
                throw new RuntimeException(e);
            }
        }

        List<String> dataFromFile = new ArrayList<>();

        try {
            dataFromFile = Files.readAllLines(path);
        } catch (FileNotFoundException e) {
            ConsoleService.writeMessage("File not found");
        } catch (IOException ex) {
            ConsoleService.writeMessage("Error read file");
            throw new RuntimeException(ex);

        }

        return dataFromFile.stream()
                .map(data -> data.split(" "))
                .map(data -> new BankCardDto(
                        Long.parseLong(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        Integer.parseInt(data[4]),
                        Boolean.parseBoolean(data[5]),
                        new BigDecimal(data[6]),
                        LocalDateTime.parse(data[7]),
                        LocalDateTime.parse(data[8]),
                        LocalDateTime.parse(data[9])))
                .collect(Collectors.toList());
    }
}
