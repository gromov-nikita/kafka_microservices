package com.gromov.dbsaver.dao.hibernate;

import com.gromov.dbsaver.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
}
