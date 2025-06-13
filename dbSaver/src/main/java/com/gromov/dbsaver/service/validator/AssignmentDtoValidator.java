package com.gromov.dbsaver.service.validator;

import com.gromov.dbsaver.dto.AssignmentDto;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AssignmentDtoValidator {
    public boolean isValid(List<AssignmentDto> assignmentDtoGroup) {
        if(StreamEx.of(assignmentDtoGroup).allMatch(this::isValid)) return true;
        else return false;
    }
    public boolean isValid(AssignmentDto assignmentDto) {
        return checkStartEndDate(assignmentDto);
    }
    public boolean checkStartEndDate(AssignmentDto assignmentDto) {
        return assignmentDto.startDate().isBefore(assignmentDto.endDate());
    }
}
