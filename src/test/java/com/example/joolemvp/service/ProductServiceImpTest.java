package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImpTest {
    @Autowired
    private ProductService service;
    @Autowired
    private ProjectService projectService;

    int id;

    @Test
    void findAll() {
        Assertions.assertNotNull(service.findAll());
    }

    @Test
    void findById() {

        Assertions.assertNotNull(service.findById(id));
    }
     @BeforeAll()
     void init() {

        Product product = new Product();
        product.setModel("String model");
        product.setSeries("String series");
        product.setManufacturer("String manufacturer");
        product.setWeight(23);
        product.setHeight_min(23);
        product.setHeightMax(2342);
        product.setSound(123);
        product.setUseType( ProductUseType.industrial);
        product.setMountingLocation(ProductMountingLocation.freeStanding);
        product.setApplication(ProductApplication.indoor);
        product.setAccessories(ProductAccessories.withoutLight);
        product.setModelYear(2000);
        product.setAirflow(12);



        service.create(product);
        id=product.getProductId();
    }

    @Test
    void findAllProductInTheProject(){
        Project project = projectService.findById(1);
        Collection<Product> products = service.findAllProductInTheProject(project);
        Assertions.assertNotNull(products);
    }
    @Test
    void findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(){
        List<Product> list = service.findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(ProductUseType.industrial, ProductApplication.indoor, ProductMountingLocation.freeStanding,
                ProductAccessories.withoutLight);
        Assertions.assertNotNull(list);
    }
    @Test
    void findProductByModelYear1(){
        List<Product> list = service.findByModelYearBetween(1990,2020);
        Assertions.assertNotNull(list);
    }
    @Test
    void findProductByModelYear2(){
        List<Product> list = service.findByModelYearBefore(2020);
        Assertions.assertNotNull(list);
    }
    @Test
    void findProductByModelYear3(){
        List<Product> list = service.findByModelYearAfter(1990);
        Assertions.assertNotNull(list);
    }
    @Test
    void findProductByAirflow(){
        List<Product> list = service.findByAirflow(0,100);
        Assertions.assertNotNull(list);
    }
    @AfterAll
    void deleteById() {

        service.deleteById(id);

    }
}