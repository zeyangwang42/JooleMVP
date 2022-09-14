package com.example.joolemvp.service.impl;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import com.example.joolemvp.Entity.User;
import com.example.joolemvp.Repository.ProjectRepository;
import com.example.joolemvp.service.ProjectProductService;
import com.example.joolemvp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private ProjectProductService projectProductService;
    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }


    @Override
    public Project createProjectForUser(User user,Project project){
        project.setUser(user);
        user.getProjects().add(project);
        repository.save(project);
        return project;
    }

    @Override
    public Project findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Project deleteById(Integer id) {
        Project project = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return project;
    }

    @Override
    public Collection<Project> findAllProjectForTheUser(User user){
        return  user.getProjects();

    }
    @Override
    public Collection<Project> findAllProjectContainsThisProduct(Product product){
        Collection<ProjectProduct> projectProducts = product.getProjectProducts();
        Collection<Project> projects = new HashSet<>();
        for(ProjectProduct projectProduct : projectProducts){
            projects.add(projectProduct.getProject());
        }
        return projects;
    }
}
