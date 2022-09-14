package com.example.joolemvp.service.impl;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import com.example.joolemvp.Repository.ProjectProductRepository;
import com.example.joolemvp.service.ProjectProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProjectProductServiceImp implements ProjectProductService {
    @Autowired
    private ProjectProductRepository repository;

    @Override
    public List<ProjectProduct> findAll() {
        return repository.findAll();
    }

    @Override
    public ProjectProduct findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProjectProduct addProductToProject(Project project, Product product) {
       ProjectProduct projectProduct = new ProjectProduct();
       projectProduct.setProduct(product);
       projectProduct.setProject(project);
       product.getProjectProducts().add(projectProduct);
       project.getProducts().add(projectProduct);
       repository.save(projectProduct);
       return projectProduct;
    }

    @Override
    public void deleteById(Integer id) {
        ProjectProduct projectProduct = repository.findById(id).orElse(null);
        if(projectProduct==null){
            return;
        }
        Project project = projectProduct.getProject();
        Product product = projectProduct.getProduct();
        product.getProjectProducts().remove(projectProduct);
        project.getProducts().remove(projectProduct);
        repository.deleteById(id);

    }
    @Override
    public ProjectProduct findByProjectAndProduct(Project project, Product product){
        Collection<ProjectProduct> list = project.getProducts();
        for(ProjectProduct projectProduct : list){
            if(projectProduct.getProduct().equals(product)){
                return projectProduct;
            }
        }
        return null;
    }
}
