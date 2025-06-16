package com.gromov.dbsaver.service.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <T> List<T> parseJson(String message,Class<T> clazz) {
        try {
            return objectMapper.readValue(message, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
