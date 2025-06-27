package com.gromov.dbsaver.service.dao.record;

import com.example.jooq.generated.tables.records.AssignmentRecord;
import com.gromov.dbsaver.dao.jooq.AssignmentJooqRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentRecordService {

    private final AssignmentJooqRepo assignmentJooqRepo;


    public void saveAll(List<AssignmentRecord> assignmentRecordGroup) {
        assignmentJooqRepo.saveAll(assignmentRecordGroup);
    }

}
