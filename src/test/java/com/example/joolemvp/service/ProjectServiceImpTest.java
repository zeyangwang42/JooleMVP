package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectServiceImpTest {
    @Autowired
    private ProjectService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    int id;

    @Test
    void findAll() {
        Assertions.assertNotNull(service.findAll());
    }


    @BeforeAll
    void createProject() {
        Project project = new Project();
        User user = new User();
        userService.createUser(user);
        project.setUser(user);
        service.createProjectForUser(user,project);
        id  = project.getProjectId();
        Assertions.assertNotNull(service.findById(id));
    }

    @Test
    void findById() {

        Assertions.assertNotNull(service.findById(id));
    }

    @Test
    void findAllProjectForTheUser(){
        User user = userService.findById(1);
        Collection<Project> projects = service.findAllProjectForTheUser(user);
        Assertions.assertNotNull(projects);
    }

    @Test
    void findAllProjectContainsThisProduct(){
        Product product = productService.findById(1);
        Collection<Project> projects = service.findAllProjectContainsThisProduct(product);
        Assertions.assertNotNull(projects);
    }
    //@AfterAll
    @Test
    void deleteById() {

        Project project = service.deleteById(id);
        //System.out.println(project);
        Assertions.assertNull(service.findById(id));

    }
}