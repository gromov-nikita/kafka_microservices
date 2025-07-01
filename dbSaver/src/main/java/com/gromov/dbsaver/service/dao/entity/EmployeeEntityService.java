package com.gromov.dbsaver.service.dao.entity;

import com.gromov.dbsaver.dao.hibernate.EmployeeHibernateRepo;
import com.gromov.dbsaver.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeEntityService {

    private final EmployeeHibernateRepo employeeHibernateRepo;


    public Employee save(Employee employee) {
        return employeeHibernateRepo.save(employee);
    }

    public List<Employee> saveAll(List<Employee> employeeGroup) {
        return employeeHibernateRepo.saveAll(employeeGroup);
    }

    public Employee findById(Integer id) {
        return employeeHibernateRepo.findById(id).orElseThrow();
    }

}
