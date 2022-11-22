package by.example.cashier.parser;

import by.example.cashier.model.dto.BankCardDto;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TxtParser implements Parser<BankCardDto> {

    @Override
    public void write(String className, List<BankCardDto> list) {
        String fileName = className + ".txt";

        final List<String> listToSave = list.stream().map(BankCardDto::toString).collect(Collectors.toList());
        try {
            Files.write(Paths.get(fileName), listToSave);
        } catch (IOException e) {
            log.error(e.toString());
            System.out.println("Error writing data to file " + fileName);
        }
    }

    @Override
    public List<BankCardDto> read(String fileName) {
        fileName += ".txt";
        final Path path = Paths.get(fileName);

        List<String> dataFromFile = new ArrayList<>();
        try {
            if (Files.exists(path)) dataFromFile = Files.readAllLines(path);
            else {
                //ConsoleService.writeMessage("Data file does not exist. A new file will be created");
                log.info("Data file does not exist. A new file will be created");
                Files.createFile(path);
            }
        } catch (IOException e) {
            log.error("Error read file");
            //ConsoleService.writeMessage("Error read file");
            throw new RuntimeException(e);
        }

        return dataFromFile.stream()
                .map(data -> data.split(" "))
                .filter(data -> mapToEntity(data) != null)
                .map(data -> mapToEntity(data))
                .collect(Collectors.toList());
    }

    private BankCardDto mapToEntity(String[] data) {
        try {
            BankCardDto bankCardDto = new BankCardDto(
                    Long.parseLong(data[0]),
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    Boolean.parseBoolean(data[5]),
                    new BigDecimal(data[6]),
                    LocalDateTime.parse(data[7]),
                    LocalDateTime.parse(data[8]),
                    LocalDateTime.parse(data[9]));
            return bankCardDto;
        } catch (DateTimeParseException e) {
            log.error("Error mapping string " + Arrays.toString(data) + "Text could not be parsed to DateTime");
        } catch (NumberFormatException e) {
            log.error("Error mapping string " + Arrays.toString(data) + "Text could not be parsed to number");
        }
        return null;
    }
}
