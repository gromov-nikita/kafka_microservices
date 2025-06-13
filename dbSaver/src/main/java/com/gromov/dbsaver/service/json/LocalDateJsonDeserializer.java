package com.gromov.dbsaver.service.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {

    private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d{1,2})(.)(\\d{1,2})\\2(\\d{4})$");

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getText();
        Matcher matcher = DATE_PATTERN.matcher(dateStr);
        if (!matcher.find()) throw new IllegalArgumentException("Invalid date format: " + dateStr);
        String delimiter = matcher.group(2);
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d" + delimiter + "M" + delimiter + "yyyy"));
    }
}