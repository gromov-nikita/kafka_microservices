package com.gromov.dbsaver.dao.hibernate;

import com.gromov.dbsaver.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectHibernateRepo extends JpaRepository<Project, Integer> {
}
