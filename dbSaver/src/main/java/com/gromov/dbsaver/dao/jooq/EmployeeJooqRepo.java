package com.gromov.dbsaver.dao.jooq;

import generated.tables.records.EmployeeRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.UpdateConditionStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static generated.tables.Employee.EMPLOYEE;

@Repository
@RequiredArgsConstructor
public class EmployeeJooqRepo {

    private final DSLContext dsl;


    public void saveOrUpdate(List<EmployeeRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> dsl.insertInto(EMPLOYEE).set(record).onConflict(EMPLOYEE.ID).doUpdate().set(record)
        ).toList()).execute();
    }


}
