package com.gromov.dbsaver.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gromov.dbsaver.service.json.LocalDateJsonDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[\\p{L} '-]+$", message = "Name must contain only letters, spaces, apostrophes or hyphens")
    @Size(max = 30, message = "Name must be at most 30 characters")
    private String name;
    private String mail;
    @JsonDeserialize(using = LocalDateJsonDeserializer.class)
    private LocalDate startWorkDate;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assignment> assignments;
}
