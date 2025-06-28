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
                record -> Objects.isNull(record.getId()) ? insert(record) : update(record)
        ).toList()).execute();
    }
    private InsertSetMoreStep<ProjectRecord> insert(ProjectRecord record) {
        return dsl.insertInto(PROJECT)
                .set(PROJECT.NAME, record.getName())
                .set(PROJECT.DESCRIPTION, record.getDescription())
                .set(PROJECT.DOMAIN, record.getDomain());
    }
    private UpdateConditionStep<ProjectRecord> update(ProjectRecord record) {
        return dsl.update(PROJECT)
                .set(PROJECT.NAME, record.getName())
                .set(PROJECT.DESCRIPTION, record.getDescription())
                .set(PROJECT.DOMAIN, record.getDomain())
                .where(PROJECT.ID.eq(record.getId()));

    }

}
