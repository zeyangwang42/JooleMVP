package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.ProductDocumentation;
import com.example.joolemvp.Entity.ProjectProduct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductDocumentationServiceImpTest {
    @Autowired
    private ProductDocumentationService service;
    @Autowired
    private ProductService productService;
    int id;
    @BeforeAll
    void create() {
        Product product = productService.findById(1);
        ProductDocumentation productDocumentation = service.addDocumentationToProduct (product,"12132333333333333");
        id=productDocumentation.getDocumentationId();
        Assertions.assertNotNull(productDocumentation.getDocumentationId());
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
        ProductDocumentation productDocumentation = service.findById(id);
        service.deleteDocumentationFromProduct(productDocumentation);

    }
}