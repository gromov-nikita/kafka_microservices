package com.gromov.dbsaver.service.mapper.entity;

import com.gromov.dbsaver.dto.AssignmentDto;
import com.gromov.dbsaver.entity.Assignment;
import com.gromov.dbsaver.service.dao.entity.EmployeeEntityService;
import com.gromov.dbsaver.service.dao.entity.ProjectEntityService;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssignmentEntityMapper {

    private final EmployeeEntityService employeeEntityService;
    private final ProjectEntityService projectEntityService;

    public Assignment toEntity(AssignmentDto dto) {
        return Assignment.builder()
                .id(dto.id())
                .employee(employeeEntityService.findById(dto.employeeId()))
                .project(projectEntityService.findById(dto.projectId()))
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
    }
    public List<Assignment> toEntity(List<AssignmentDto> dto) {
        return StreamEx.of(dto).map(this::toEntity).toList();
    }
}
