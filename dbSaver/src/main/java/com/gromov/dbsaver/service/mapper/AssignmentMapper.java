package com.gromov.dbsaver.service.mapper;

import com.gromov.dbsaver.dto.AssignmentDto;
import generated.tables.records.AssignmentRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AssignmentMapper {

    AssignmentDto toDto(AssignmentRecord record);

    List<AssignmentDto> toDto(List<AssignmentRecord> recordGroup);

    AssignmentRecord toRecord(AssignmentDto dto);

    List<AssignmentRecord> toRecord(List<AssignmentDto> dtoGroup);

}
