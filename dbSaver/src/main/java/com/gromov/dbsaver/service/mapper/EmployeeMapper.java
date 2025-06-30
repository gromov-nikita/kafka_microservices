package com.gromov.dbsaver.service.mapper;

import com.gromov.dbsaver.dto.EmployeeDto;
import generated.tables.records.EmployeeRecord;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeMapper {

    EmployeeDto toDto(EmployeeRecord record);

    List<EmployeeDto> toDto(List<EmployeeRecord> recordGroup);

    EmployeeRecord toRecord(EmployeeDto dto);

    List<EmployeeRecord> toRecord(List<EmployeeDto> dtoGroup);

}
