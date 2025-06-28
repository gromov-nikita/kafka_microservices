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

    public ProjectRecord toRecord(ProjectDto dto) {
        return new ProjectRecord(
                dto.id(),
                dto.name(),
                dto.description(),
                Domain.lookupLiteral(dto.domain())
        );
    }
    public List<ProjectRecord> toRecord(List<ProjectDto> dto) {
        return StreamEx.of(dto).map(this::toRecord).toList();
    }

}
