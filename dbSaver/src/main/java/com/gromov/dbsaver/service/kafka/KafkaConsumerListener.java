package com.gromov.dbsaver.service.kafka;

import com.gromov.dbsaver.dto.AssignmentDto;
import com.gromov.dbsaver.dto.EmployeeDto;
import com.gromov.dbsaver.dto.ProjectDto;
import com.gromov.dbsaver.service.dao.record.AssignmentRecordService;
import com.gromov.dbsaver.service.dao.record.EmployeeRecordService;
import com.gromov.dbsaver.service.dao.record.ProjectRecordService;
import com.gromov.dbsaver.service.grpc.GrpcNotificationService;
import com.gromov.dbsaver.service.json.JsonParser;
import com.gromov.dbsaver.service.mapper.AssignmentMapper;
import com.gromov.dbsaver.service.mapper.EmployeeMapper;
import com.gromov.dbsaver.service.mapper.ProjectMapper;
import com.gromov.dbsaver.service.validator.AssignmentDtoValidator;
import com.gromov.dbsaver.service.validator.EmployeeDtoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerListener {

    private final EmployeeRecordService employeeRecordService;

    private final AssignmentRecordService assignmentRecordService;

    private final ProjectRecordService projectRecordService;

    private final AssignmentMapper assignmentMapper;

    private final EmployeeMapper employeeMapper;

    private final ProjectMapper projectMapper;

    private final AssignmentDtoValidator assignmentDtoValidator;

    private final EmployeeDtoValidator employeeDtoValidator;

    private final GrpcNotificationService grpcNotificationService;

    private final JsonParser jsonParser;

    private static final String ASSIGNMENT_NOT_VALID_MESSAGE = "Start date must be before end date.";

    private static final String EMPLOYEE_NOT_VALID_MESSAGE = "Wrong name.";

    private static final String LOG_ERROR_MESSAGE = "Exception caught: ";


    @KafkaListener(topics = "${spring.kafka.topic-name.employee}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeEmployee(String message) {
        List<EmployeeDto> dtoGroup = jsonParser.parseJson(message, EmployeeDto.class);
        if (employeeDtoValidator.isValid(dtoGroup)) {
            employeeRecordService.saveAll(employeeMapper.toRecord(dtoGroup));
        }
        else {
            grpcNotificationService.notValidNotify(EMPLOYEE_NOT_VALID_MESSAGE);
        }
    }

    @KafkaListener(topics = "${spring.kafka.topic-name.assignment}", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void consumeAssignment(String message) {
        List<AssignmentDto> dtoGroup = jsonParser.parseJson(message, AssignmentDto.class);
        if(assignmentDtoValidator.isValid(dtoGroup)) {
            assignmentRecordService.saveAll(assignmentMapper.toRecord(dtoGroup));
        }
        else {
            grpcNotificationService.notValidNotify(ASSIGNMENT_NOT_VALID_MESSAGE);
        }
    }

    @KafkaListener(topics = "${spring.kafka.topic-name.project}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeProject(String message) {
        projectRecordService.saveAll(projectMapper.toRecord(jsonParser.parseJson(message, ProjectDto.class)));
    }

}


