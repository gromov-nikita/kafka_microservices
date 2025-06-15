package com.gromov.csvReader.service.parser.csv;

import com.gromov.csvReader.exception.CsvFileException;
import com.gromov.csvReader.service.parser.Parser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class CsvParser implements Parser {

    @Value("${csv.path}")
    @Setter
    private String path;

    private static final String exceptionMessage = "Не удалось прочитать CSV-файл";

    @Override
    public <T> List<T> parse(String fileName,Class<T> clazz) {
        String fullPath = path+"\\"+fileName;
        try(FileReader reader = new FileReader(fullPath)) {
            List<T> parse = getCsvToBean(reader, clazz).parse();
            Files.newBufferedWriter(Paths.get(fullPath), StandardOpenOption.TRUNCATE_EXISTING).close();
            return parse;
        } catch (IOException e) {
            throw new CsvFileException(exceptionMessage,e);
        }
    }
    private <T> CsvToBean<T> getCsvToBean(FileReader reader, Class<T> clazz) {
        return new CsvToBeanBuilder<T>(reader)
                .withType(clazz)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }
}
