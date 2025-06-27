package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.AssignmentRecord;
import com.example.jooq.generated.tables.records.ProjectRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.InsertOnDuplicateSetMoreStep;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.example.jooq.generated.tables.Project.PROJECT;

@Repository
@RequiredArgsConstructor
public class ProjectJooqRepo {

    private final DSLContext dsl;

    public void saveAll(List<ProjectRecord> recordGroup) {
        dsl.batch(StreamEx.of(recordGroup).map(
                record -> Objects.isNull(record.getId()) ? saveAllWithoutId(record) : saveAllWithId(record)
        ).toList()).execute();
    }
    private InsertSetMoreStep<ProjectRecord> saveAllWithoutId(ProjectRecord record) {
        return dsl.insertInto(PROJECT)
                .set(PROJECT.NAME, record.getName())
                .set(PROJECT.DESCRIPTION, record.getDescription())
                .set(PROJECT.DOMAIN, record.getDomain());
    }
    private InsertOnDuplicateSetMoreStep<ProjectRecord> saveAllWithId(ProjectRecord record) {
        return dsl.insertInto(PROJECT)
                .set(PROJECT.ID, record.getId())
                .set(PROJECT.NAME, record.getName())
                .set(PROJECT.DESCRIPTION, record.getDescription())
                .set(PROJECT.DOMAIN, record.getDomain())
                .onConflict(PROJECT.ID).doUpdate()
                .set(PROJECT.NAME, record.getName())
                .set(PROJECT.DESCRIPTION, record.getDescription())
                .set(PROJECT.DOMAIN, record.getDomain());
    }

}
