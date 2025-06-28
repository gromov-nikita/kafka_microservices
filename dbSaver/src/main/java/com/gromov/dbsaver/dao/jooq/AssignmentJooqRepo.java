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
                record -> dsl.insertInto(ASSIGNMENT).set(record).onConflict(ASSIGNMENT.ID).doUpdate().set(record)
        ).toList()).execute();
    }
}
