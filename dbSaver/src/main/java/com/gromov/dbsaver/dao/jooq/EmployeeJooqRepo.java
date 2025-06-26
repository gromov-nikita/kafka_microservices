package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.EmployeeRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jooq.generated.tables.Employee.EMPLOYEE;

@Repository
@RequiredArgsConstructor
public class EmployeeJooqRepo {

    private final DSLContext dsl;

    public void saveAll(List<EmployeeRecord> records) {
        dsl.batch(StreamEx.of(records).map(
                        r -> dsl.insertInto(EMPLOYEE)
                                .set(EMPLOYEE.ID, r.getId())
                                .set(EMPLOYEE.NAME, r.getName())
                                .set(EMPLOYEE.MAIL, r.getMail())
                                .set(EMPLOYEE.START_WORK_DATE, r.getStartWorkDate())
                                .onConflict(EMPLOYEE.ID).doUpdate()
                                .set(EMPLOYEE.NAME, r.getName())
                                .set(EMPLOYEE.MAIL, r.getMail())
                                .set(EMPLOYEE.START_WORK_DATE, r.getStartWorkDate())
        ).toList()).execute();
    }

}
