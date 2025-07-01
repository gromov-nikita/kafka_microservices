package com.gromov.dbsaver.service.dao.entity;

import com.gromov.dbsaver.dao.hibernate.AssignmentHibernateRepo;
import com.gromov.dbsaver.entity.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentEntityService {

    private final AssignmentHibernateRepo assignmentHibernateRepo;


    public Assignment save(Assignment assignment) {
        return assignmentHibernateRepo.save(assignment);
    }

    public List<Assignment> saveAll(List<Assignment> assignmentGroup) {
        return assignmentHibernateRepo.saveAll(assignmentGroup);
    }

}
