package com.gromov.dbsaver.service.dao;

import com.gromov.dbsaver.dao.AssignmentRepo;
import com.gromov.dbsaver.entity.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepo assignmentRepo;
    public Assignment save(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }
    public List<Assignment> saveAll(List<Assignment> assignmentGroup) {
        return assignmentRepo.saveAll(assignmentGroup);
    }
}
