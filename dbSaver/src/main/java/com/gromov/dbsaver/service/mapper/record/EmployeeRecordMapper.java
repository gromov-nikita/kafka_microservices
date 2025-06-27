package com.gromov.dbsaver.service.mapper.record;

import com.example.jooq.generated.tables.records.EmployeeRecord;
import com.gromov.dbsaver.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.jooq.generated.tables.Employee.EMPLOYEE;

@Component
@RequiredArgsConstructor
public class EmployeeRecordMapper {

    private final DSLContext dsl;

    public EmployeeRecord toRecord(EmployeeDto dto) {
        return new EmployeeRecord(
                dto.id(),
                dto.name(),
                dto.mail(),
                dto.startWorkDate()
        );

    }
    public List<EmployeeRecord> toRecord(List<EmployeeDto> dto) {
        return StreamEx.of(dto).map(this::toRecord).toList();
    }

}
