package by.example.cashier.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonParser<T> implements Parser<T> {

    private String fileDirectory;

    @SneakyThrows
    @Override
    public void write(String className, List<T> list) {
        String fileName = className + ".json";
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(fileDirectory + fileName), list);

        log.info("List has been loaded to {}", fileDirectory + fileName);
    }

    @Override
    public List<T> read(String fileName) {
        String fullFileName = fileName + ".json";
        ObjectMapper mapper = new ObjectMapper();

        List<T> decoded = null;
        try {
            decoded = mapper.readValue(
                    new File(fullFileName),
                    new TypeReference<List<T>>() {
                    });
        } catch (IOException e) {
            log.error("was not loaded from {}", fileDirectory + fullFileName);
            return null;
        }
        log.info("List has been loaded from {}", fileDirectory + fullFileName);
        return decoded;
    }
}
