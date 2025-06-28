package com.gromov.dbsaver.dao.jooq;

import generated.tables.records.ProjectRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.UpdateConditionStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static generated.tables.Project.PROJECT;

@Repository
@RequiredArgsConstructor
public class ProjectJooqRepo {

    private final DSLContext dsl;

    public void saveOrUpdate(List<ProjectRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> dsl.insertInto(PROJECT).set(record).onConflict(PROJECT.ID).doUpdate().set(record)
        ).toList()).execute();
    }

}
