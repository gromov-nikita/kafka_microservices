package com.gromov.dbsaver.service.dao;

import com.gromov.dbsaver.dao.EmployeeRepo;
import com.gromov.dbsaver.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }
    public List<Employee> saveAll(List<Employee> employeeGroup) {
        return employeeRepo.saveAll(employeeGroup);
    }
    public Employee findById(Integer id) {
        return employeeRepo.findById(id).orElse(null);
    }
}
