package com.gromov.dbsaver.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.gromov.dbsaver.service.json.LocalDateJsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public record AssignmentDto (
        Integer id,
        String fileName,
        Integer employeeId,
        Integer projectId,
        @JsonDeserialize(using = LocalDateJsonDeserializer.class) LocalDate startDate,
        @JsonDeserialize(using = LocalDateJsonDeserializer.class) LocalDate endDate
){}
