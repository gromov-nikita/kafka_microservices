package com.gromov.dbsaver.service.dao.record;

import com.example.jooq.generated.tables.records.AssignmentRecord;
import com.example.jooq.generated.tables.records.EmployeeRecord;
import com.gromov.dbsaver.dao.jooq.EmployeeJooqRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRecordService {

    private final EmployeeJooqRepo employeeJooqRepo;


    public void saveAll(List<EmployeeRecord> employeeRecordGroup) {
        employeeJooqRepo.saveAll(employeeRecordGroup);
    }

}
