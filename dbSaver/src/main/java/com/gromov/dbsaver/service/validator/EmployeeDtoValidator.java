package com.gromov.dbsaver.service.validator;

import com.gromov.dbsaver.dto.AssignmentDto;
import com.gromov.dbsaver.dto.EmployeeDto;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDtoValidator {

    private static final String NAME_REGEX = "^(?=.*\\p{L})[\\p{L} '-]{1,30}$";


    public boolean isValid(List<EmployeeDto> employeeDtoGroup) {
        return StreamEx.of(employeeDtoGroup).allMatch(this::isValid);
    }

    public boolean isValid(EmployeeDto employeeDto) {
        return isNameValid(employeeDto.name());
    }

    private boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

}
