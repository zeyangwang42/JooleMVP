package com.example.joolemvp.Repository;

import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import com.example.joolemvp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    //void deleteByProjects(ProjectProduct projectProduct);
}
