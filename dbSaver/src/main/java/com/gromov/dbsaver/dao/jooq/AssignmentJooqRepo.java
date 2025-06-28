package com.gromov.dbsaver.dao.jooq;

import generated.tables.records.AssignmentRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.UpdateConditionStep;
import org.springframework.stereotype.Repository;
import static generated.tables.Assignment.ASSIGNMENT;

import java.util.List;
import java.util.Objects;


@Repository
@RequiredArgsConstructor
public class AssignmentJooqRepo {

    private final DSLContext dsl;

    public void saveOrUpdate(List<AssignmentRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> Objects.isNull(record.getId()) ? insert(record) : update(record)
        ).toList()).execute();
    }
    private InsertSetMoreStep<AssignmentRecord> insert(AssignmentRecord record) {
        return dsl.insertInto(ASSIGNMENT)
                .set(ASSIGNMENT.START_DATE, record.getStartDate())
                .set(ASSIGNMENT.END_DATE, record.getEndDate())
                .set(ASSIGNMENT.EMPLOYEE_ID, record.getEmployeeId())
                .set(ASSIGNMENT.PROJECT_ID, record.getProjectId());
    }
    private UpdateConditionStep<AssignmentRecord> update(AssignmentRecord record) {
        return dsl.update(ASSIGNMENT)
                .set(ASSIGNMENT.START_DATE, record.getStartDate())
                .set(ASSIGNMENT.END_DATE, record.getEndDate())
                .set(ASSIGNMENT.EMPLOYEE_ID, record.getEmployeeId())
                .set(ASSIGNMENT.PROJECT_ID, record.getProjectId())
                .where(ASSIGNMENT.ID.eq(record.getId()));
    }
}
