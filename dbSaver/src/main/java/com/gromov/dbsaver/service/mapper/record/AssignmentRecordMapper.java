package com.gromov.dbsaver.service.mapper.record;

import com.gromov.dbsaver.dto.AssignmentDto;
import generated.tables.Assignment;
import generated.tables.records.AssignmentRecord;
import org.jooq.Attachable;
import org.jooq.ContextConverter;
import org.jooq.ConverterContext;
import org.jooq.Field;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

import static generated.Tables.ASSIGNMENT;

@Mapper(componentModel = "spring")
public interface AssignmentRecordMapper {

    AssignmentDto toDto(AssignmentRecord record);

    List<AssignmentDto> toDto(List<AssignmentRecord> recordGroup);

    AssignmentRecord toRecord(AssignmentDto dto);

    List<AssignmentRecord> toRecord(List<AssignmentDto> dtoGroup);

    @AfterMapping
    default void afterMapping(@MappingTarget AssignmentRecord record) {
        if(Objects.isNull(record.getId())) record.changed(ASSIGNMENT.ID,false);
    }

}
