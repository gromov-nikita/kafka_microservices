package com.gromov.dbsaver.service.dao.record;

import generated.tables.records.EmployeeRecord;
import com.gromov.dbsaver.dao.jooq.EmployeeJooqRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRecordService {

    private final EmployeeJooqRepo employeeJooqRepo;


    public void saveAll(List<EmployeeRecord> employeeRecordGroup) {
        employeeJooqRepo.saveOrUpdate(employeeRecordGroup);
    }

}


