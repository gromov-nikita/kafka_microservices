package com.gromov.dbsaver.service.mapper;

import com.gromov.dbsaver.dto.AssignmentDto;
import com.gromov.dbsaver.entity.Assignment;
import com.gromov.dbsaver.service.dao.EmployeeService;
import com.gromov.dbsaver.service.dao.ProjectService;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssignmentMapper {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public Assignment toEntity(AssignmentDto dto) {
        return Assignment.builder()
                .id(dto.id())
                .employee(employeeService.findById(dto.employeeId()))
                .project(projectService.findById(dto.projectId()))
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
    }
    public List<Assignment> toEntity(List<AssignmentDto> dto) {
        return StreamEx.of(dto).map(this::toEntity).toList();
    }
}
