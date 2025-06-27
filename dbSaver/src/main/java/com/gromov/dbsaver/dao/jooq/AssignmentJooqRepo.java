package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.AssignmentRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertOnDuplicateSetMoreStep;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;
import static com.example.jooq.generated.tables.Assignment.ASSIGNMENT;

import java.util.List;
import java.util.Objects;


@Repository
@RequiredArgsConstructor
public class AssignmentJooqRepo {

    private final DSLContext dsl;

    public void saveAll(List<AssignmentRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> Objects.isNull(record.getId()) ? saveAllWithoutId(record) : saveAllWithId(record)
        ).toList()).execute();
    }
    private InsertSetMoreStep<AssignmentRecord> saveAllWithoutId(AssignmentRecord record) {
        return dsl.insertInto(ASSIGNMENT)
                .set(ASSIGNMENT.START_DATE, record.getStartDate())
                .set(ASSIGNMENT.END_DATE, record.getEndDate())
                .set(ASSIGNMENT.EMPLOYEE_ID, record.getEmployeeId())
                .set(ASSIGNMENT.PROJECT_ID, record.getProjectId());
    }
    private InsertOnDuplicateSetMoreStep<AssignmentRecord> saveAllWithId(AssignmentRecord record) {
        return dsl.insertInto(ASSIGNMENT)
                .set(ASSIGNMENT.START_DATE, record.getStartDate())
                .set(ASSIGNMENT.END_DATE, record.getEndDate())
                .set(ASSIGNMENT.EMPLOYEE_ID, record.getEmployeeId())
                .set(ASSIGNMENT.PROJECT_ID, record.getProjectId())
                .onConflict(ASSIGNMENT.ID).doUpdate()
                .set(ASSIGNMENT.START_DATE, record.getStartDate())
                .set(ASSIGNMENT.END_DATE, record.getEndDate())
                .set(ASSIGNMENT.EMPLOYEE_ID, record.getEmployeeId())
                .set(ASSIGNMENT.PROJECT_ID, record.getProjectId());
    }
    private InsertSetMoreStep<AssignmentRecord> handleNullId(Integer id, InsertSetMoreStep<AssignmentRecord> insert) {
        if(Objects.nonNull(id)) {
            insert.set(ASSIGNMENT.ID, id);
        }
        return insert;
    }




}
