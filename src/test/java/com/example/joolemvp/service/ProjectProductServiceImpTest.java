package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectProductServiceImpTest {
    @Autowired
    private ProjectProductService service;
    @Autowired
    private ProductService service1;
    @Autowired
    private ProjectService service2;
    int id;
    @BeforeAll
    void createProjectProduct() {
        Project project = service2.findById(1);
        Product product =service1.findById(1);

        ProjectProduct projectProduct = service.addProductToProject (project, product);
        id=projectProduct.getProject_product_id();

    }
    @Test
    void findAll() {
        Assertions.assertNotNull(service.findAll());

    }

    @Test
    void findById() {
        Assertions.assertNotNull(service.findById(id));
    }



    @AfterAll
    void deleteById() {
        service.deleteById(id);
    }
}