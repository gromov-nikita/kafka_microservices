package com.gromov.dbsaver.service.dao.record;

import com.example.jooq.generated.tables.records.EmployeeRecord;
import com.example.jooq.generated.tables.records.ProjectRecord;
import com.gromov.dbsaver.dao.jooq.ProjectJooqRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectRecordService {

    private final ProjectJooqRepo projectJooqRepo;

    public void saveAll(List<ProjectRecord> projectRecordGroup) {
        projectJooqRepo.saveAll(projectRecordGroup);
    }

}
