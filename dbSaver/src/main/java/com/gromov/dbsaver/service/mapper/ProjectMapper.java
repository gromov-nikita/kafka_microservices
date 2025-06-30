package com.gromov.dbsaver.service.mapper;

import com.gromov.dbsaver.dto.ProjectDto;
import generated.tables.records.ProjectRecord;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProjectMapper {

    ProjectDto toDto(ProjectRecord record);

    List<ProjectDto> toDto(List<ProjectRecord> recordGroup);

    ProjectRecord toRecord(ProjectDto dto);

    List<ProjectRecord> toRecord(List<ProjectDto> dtoGroup);

}
