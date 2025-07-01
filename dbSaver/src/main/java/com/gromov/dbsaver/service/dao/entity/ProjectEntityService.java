package com.gromov.dbsaver.service.dao.entity;

import com.gromov.dbsaver.dao.hibernate.ProjectHibernateRepo;
import com.gromov.dbsaver.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectEntityService {

    private final ProjectHibernateRepo projectHibernateRepo;


    public Project save(Project project) {
        return projectHibernateRepo.save(project);
    }

    public List<Project> saveAll(List<Project> projectGroup) {
        return projectHibernateRepo.saveAll(projectGroup);
    }

    public Project findById(Integer id) {
        return projectHibernateRepo.findById(id).orElseThrow();
    }

}
