package com.gromov.dbsaver.service.kafka;

import com.gromov.dbsaver.dto.AssignmentDto;
import com.gromov.dbsaver.entity.Employee;
import com.gromov.dbsaver.entity.Project;
import com.gromov.dbsaver.service.dao.AssignmentService;
import com.gromov.dbsaver.service.dao.EmployeeService;
import com.gromov.dbsaver.service.dao.ProjectService;
import com.gromov.dbsaver.service.grpc.GrpcNotificationService;
import com.gromov.dbsaver.service.json.JsonParser;
import com.gromov.dbsaver.service.mapper.AssignmentMapper;
import com.gromov.dbsaver.service.validator.AssignmentDtoValidator;
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

    private final EmployeeService employeeService;
    private final AssignmentService assignmentService;
    private final ProjectService projectService;
    private final AssignmentMapper assignmentMapper;
    private final AssignmentDtoValidator assignmentDtoValidator;
    private final GrpcNotificationService grpcNotificationService;
    private final JsonParser jsonParser;

    private static final String assignmentNotValidMessage = "Start date must be before end date.";

    @KafkaListener(topics = "${spring.kafka.topic-name.employee}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeEmployee(String message) {
        try {
            employeeService.saveAll(jsonParser.parseJson(message, Employee.class));
        }
        catch (Exception e) {
            grpcNotificationService.notValidNotify(e.getMessage());
            log.error("Exception caught: ", e);
        }
    }
    @KafkaListener(topics = "${spring.kafka.topic-name.assignment}", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void consumeAssignment(String message) {
        List<AssignmentDto> dtoGroup = jsonParser.parseJson(message, AssignmentDto.class);
        if(assignmentDtoValidator.isValid(dtoGroup)) assignmentService.saveAll(assignmentMapper.toEntity(dtoGroup));
        else grpcNotificationService.notValidNotify(assignmentNotValidMessage);
    }
    @KafkaListener(topics = "${spring.kafka.topic-name.project}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeProject(String message) {
        projectService.saveAll(jsonParser.parseJson(message, Project.class));
    }

}
