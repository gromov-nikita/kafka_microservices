package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.EmployeeRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.UpdateConditionStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.example.jooq.generated.tables.Employee.EMPLOYEE;

@Repository
@RequiredArgsConstructor
public class EmployeeJooqRepo {

    private final DSLContext dsl;


    public void saveOrUpdate(List<EmployeeRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> Objects.isNull(record.getId()) ? insert(record) : update(record)
        ).toList()).execute();
    }
    private InsertSetMoreStep<EmployeeRecord> insert(EmployeeRecord record) {
        return dsl.insertInto(EMPLOYEE)
                .set(EMPLOYEE.NAME, record.getName())
                .set(EMPLOYEE.MAIL, record.getMail())
                .set(EMPLOYEE.START_WORK_DATE, record.getStartWorkDate());
    }
    private UpdateConditionStep<EmployeeRecord> update(EmployeeRecord record) {
        return dsl.update(EMPLOYEE)
                .set(EMPLOYEE.NAME, record.getName())
                .set(EMPLOYEE.MAIL, record.getMail())
                .set(EMPLOYEE.START_WORK_DATE, record.getStartWorkDate())
                .where(EMPLOYEE.ID.eq(record.getId()));
    }

}
