package com.gromov.dbsaver.service.mapper.record;

import com.example.jooq.generated.enums.Domain;
import com.example.jooq.generated.tables.records.ProjectRecord;
import com.gromov.dbsaver.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.jooq.generated.tables.Project.PROJECT;

@Component
@RequiredArgsConstructor
public class ProjectRecordMapper {

    private final DSLContext dsl;

    public ProjectRecord toRecord(ProjectDto dto) {
        return dsl.newRecord(PROJECT)
                .with(PROJECT.ID, dto.id())
                .with(PROJECT.NAME, dto.name())
                .with(PROJECT.DESCRIPTION, dto.description())
                .with(PROJECT.DOMAIN, Domain.lookupLiteral(dto.domain()));

    }
    public List<ProjectRecord> toRecord(List<ProjectDto> dto) {
        return StreamEx.of(dto).map(this::toRecord).toList();
    }

}
