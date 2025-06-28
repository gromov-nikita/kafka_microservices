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

    public AssignmentRecord toRecord(AssignmentDto dto) {
        return new AssignmentRecord(
                dto.id(),
                dto.startDate(),
                dto.endDate(),
                dto.employeeId(),
                dto.projectId()
        );
    }
    public List<AssignmentRecord> toRecord(List<AssignmentDto> dto) {
        return StreamEx.of(dto).map(this::toRecord).toList();
    }
}
