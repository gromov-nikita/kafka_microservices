package com.gromov.dbsaver.service.mapper.record;

import com.gromov.dbsaver.dto.EmployeeDto;
import generated.tables.records.EmployeeRecord;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.Objects;

import static generated.Tables.EMPLOYEE;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeRecordMapper {

    EmployeeDto toDto(EmployeeRecord record);

    List<EmployeeDto> toDto(List<EmployeeRecord> recordGroup);

    EmployeeRecord toRecord(EmployeeDto dto);

    List<EmployeeRecord> toRecord(List<EmployeeDto> dtoGroup);

}
