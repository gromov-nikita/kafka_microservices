package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.AssignmentRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import static com.example.jooq.generated.tables.Assignment.ASSIGNMENT;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class AssignmentJooqRepo {

    private final DSLContext dsl;

    public void saveAll(List<AssignmentRecord> records) {
        dsl.batch(StreamEx.of(records).map(
                r -> dsl.insertInto(ASSIGNMENT)
                        .set(ASSIGNMENT.ID, r.getId())
                        .set(ASSIGNMENT.START_DATE, r.getStartDate())
                        .set(ASSIGNMENT.END_DATE, r.getEndDate())
                        .set(ASSIGNMENT.EMPLOYEE_ID, r.getEmployeeId())
                        .set(ASSIGNMENT.PROJECT_ID, r.getProjectId())
                        .onConflict(ASSIGNMENT.ID).doUpdate().set(ASSIGNMENT.START_DATE, r.getStartDate())
                        .set(ASSIGNMENT.END_DATE, r.getEndDate()).set(ASSIGNMENT.EMPLOYEE_ID, r.getEmployeeId())
                        .set(ASSIGNMENT.PROJECT_ID, r.getProjectId())
        ).toList()).execute();
    }




}
