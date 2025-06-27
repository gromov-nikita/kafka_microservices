package com.gromov.dbsaver.service.mapper.record;

import com.example.jooq.generated.tables.records.AssignmentRecord;
import com.gromov.dbsaver.dto.AssignmentDto;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.jooq.generated.tables.Assignment.ASSIGNMENT;

@Component
@RequiredArgsConstructor
public class AssignmentRecordMapper {

    private final DSLContext dsl;

    public AssignmentRecord toRecord(AssignmentDto dto) {
        return dsl.newRecord(ASSIGNMENT)
                .with(ASSIGNMENT.ID, dto.id())
                .with(ASSIGNMENT.START_DATE, dto.startDate())
                .with(ASSIGNMENT.END_DATE, dto.endDate())
                .with(ASSIGNMENT.EMPLOYEE_ID, dto.employeeId())
                .with(ASSIGNMENT.PROJECT_ID, dto.projectId());

    }
    public List<AssignmentRecord> toRecord(List<AssignmentDto> dto) {
        return StreamEx.of(dto).map(this::toRecord).toList();
    }
}
