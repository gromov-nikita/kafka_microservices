package com.gromov.csvReader;

import com.gromov.csvReader.dto.Employee;
import com.gromov.csvReader.service.parser.csv.CsvParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTests {

    private static final CsvParser parser = new CsvParser();

    private Path tempDir;

    private static final String ERROR_MESSAGE = "Failed to delete ";

    private static final String FILE_NAME = "test.csv";

    private static final String CSV_EMPLOYEE = "id,name,mail,startWorkDate\n" +
            ",John Doe,john.doe@example.com,01-03-2020\n" +
            ",Jane Smith,jane.smith@example.com,12/05/2021\n" +
            ",Michael Brown,michael.brown@example.com,25.07.2019\n";

    private static final String TEMP_DIRECTORY_NAME = "csvTest";


    @BeforeEach
    void setup() throws IOException {
        tempDir = Files.createTempDirectory(TEMP_DIRECTORY_NAME);
        parser.setPath(tempDir.toString());
        Path csvFile = tempDir.resolve(FILE_NAME);
        Files.writeString(csvFile, CSV_EMPLOYEE);
    }
    @AfterEach
    void cleanup() throws IOException {
        try (var files = Files.walk(tempDir)) {
            files.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        if (!file.delete()) {
                            System.err.println(ERROR_MESSAGE + file);
                        }
                    });
        }
    }

    @Test
    public void parseEmployeeTest() {
        List<Employee> records = parser.parse(FILE_NAME, Employee.class);

        assertEquals(3, records.size());

        assertEquals("John Doe", records.get(0).getName());
        assertEquals("john.doe@example.com", records.get(0).getMail());
        assertEquals("01-03-2020", records.get(0).getStartWorkDate());

        assertEquals("Jane Smith", records.get(1).getName());
        assertEquals("jane.smith@example.com", records.get(1).getMail());
        assertEquals("12/05/2021", records.get(1).getStartWorkDate());

        assertEquals("Michael Brown", records.get(2).getName());
        assertEquals("michael.brown@example.com", records.get(2).getMail());
        assertEquals("25.07.2019", records.get(2).getStartWorkDate());
    }

}
