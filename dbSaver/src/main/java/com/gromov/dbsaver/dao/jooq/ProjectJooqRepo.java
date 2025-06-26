package com.gromov.dbsaver.dao.jooq;

import com.example.jooq.generated.tables.records.ProjectRecord;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jooq.generated.tables.Project.PROJECT;

@Repository
@RequiredArgsConstructor
public class ProjectJooqRepo {

    private final DSLContext dsl;

    public void saveAll(List<ProjectRecord> records) {
        dsl.batch(StreamEx.of(records).map(
                r -> dsl.insertInto(PROJECT)
                        .set(PROJECT.ID, r.getId())
                        .set(PROJECT.NAME, r.getName())
                        .set(PROJECT.DESCRIPTION, r.getDescription())
                        .set(PROJECT.DOMAIN, r.getDomain())
                        .onConflict(PROJECT.ID).doUpdate()
                        .set(PROJECT.NAME, r.getName())
                        .set(PROJECT.DESCRIPTION, r.getDescription())
                        .set(PROJECT.DOMAIN, r.getDomain())
        ).toList()).execute();
    }

}
