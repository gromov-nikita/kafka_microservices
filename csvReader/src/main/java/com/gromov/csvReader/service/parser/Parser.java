package com.gromov.csvReader.service.parser;

import java.util.List;

public interface Parser {
    <T> List<T> parse(String fileName, Class<T> clazz);
}
