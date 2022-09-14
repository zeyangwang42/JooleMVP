package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.User;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    public List<Project> findAll();

    public Project createProjectForUser(User user,Project project);

    public Project findById(Integer id);

    public Project deleteById(Integer id);

    public Collection<Project> findAllProjectForTheUser(User user);

    public Collection<Project> findAllProjectContainsThisProduct(Product product);

}
