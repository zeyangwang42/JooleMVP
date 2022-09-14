package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;

import java.util.List;
import java.util.Optional;

public interface ProjectProductService {
    public List<ProjectProduct> findAll();

    public ProjectProduct findById(Integer id);

    public ProjectProduct addProductToProject( Project project, Product product);

    public ProjectProduct findByProjectAndProduct(Project project, Product product);

    public void deleteById(Integer id);
}
