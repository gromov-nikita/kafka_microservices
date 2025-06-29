package com.gromov.dbsaver.service.mapper.record;

import com.gromov.dbsaver.dto.ProjectDto;
import generated.tables.records.ProjectRecord;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

import static generated.Tables.PROJECT;

@Mapper(componentModel = "spring")
public interface ProjectRecordMapper {

    ProjectDto toDto(ProjectRecord record);

    List<ProjectDto> toDto(List<ProjectRecord> recordGroup);

    ProjectRecord toRecord(ProjectDto dto);

    List<ProjectRecord> toRecord(List<ProjectDto> dtoGroup);

    @AfterMapping
    default void afterMapping(@MappingTarget ProjectRecord record) {
        if(Objects.isNull(record.getId())) record.changed(PROJECT.ID,false);
    }

}
