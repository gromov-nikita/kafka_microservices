package com.gromov.dbsaver.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gromov.dbsaver.service.json.LocalDateJsonDeserializer;

import java.time.LocalDate;

public record EmployeeDto(
        Integer id,
        String name,
        String mail,
        @JsonDeserialize(using = LocalDateJsonDeserializer.class) LocalDate startWorkDate
){}
