package com.gromov.dbsaver.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gromov.dbsaver.service.json.LocalDateJsonDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Project project;
    @JsonDeserialize(using = LocalDateJsonDeserializer.class)
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateJsonDeserializer.class)
    private LocalDate endDate;
}
