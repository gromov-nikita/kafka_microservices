package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.EmployeeRecord;
import com.example.jooq.generated.tables.records.ProjectRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertOnDuplicateSetMoreStep;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.example.jooq.generated.tables.Employee.EMPLOYEE;

@Repository
@RequiredArgsConstructor
public class EmployeeJooqRepo {

    private final DSLContext dsl;


    public void saveAll(List<EmployeeRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> Objects.isNull(record.getId()) ? saveAllWithoutId(record) : saveAllWithId(record)
        ).toList()).execute();
    }
    private InsertSetMoreStep<EmployeeRecord> saveAllWithoutId(EmployeeRecord record) {
        return dsl.insertInto(EMPLOYEE)
                .set(EMPLOYEE.NAME, record.getName())
                .set(EMPLOYEE.MAIL, record.getMail())
                .set(EMPLOYEE.START_WORK_DATE, record.getStartWorkDate());
    }
    private InsertOnDuplicateSetMoreStep<EmployeeRecord> saveAllWithId(EmployeeRecord record) {
        return dsl.insertInto(EMPLOYEE)
                .set(EMPLOYEE.ID, record.getId())
                .set(EMPLOYEE.NAME, record.getName())
                .set(EMPLOYEE.MAIL, record.getMail())
                .set(EMPLOYEE.START_WORK_DATE, record.getStartWorkDate())
                .onConflict(EMPLOYEE.ID).doUpdate()
                .set(EMPLOYEE.NAME, record.getName())
                .set(EMPLOYEE.MAIL, record.getMail())
                .set(EMPLOYEE.START_WORK_DATE, record.getStartWorkDate());
    }

}
