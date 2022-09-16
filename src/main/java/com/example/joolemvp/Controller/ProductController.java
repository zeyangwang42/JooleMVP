package com.example.joolemvp.Controller;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.ProductDocumentation;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import com.example.joolemvp.service.ProductDocumentationService;
import com.example.joolemvp.service.ProductService;
import com.example.joolemvp.service.ProjectProductService;
import com.example.joolemvp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ProductController")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectProductService service;
    @Autowired
    private ProductDocumentationService documentationService;

    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAll() {
        List<Product> list =productService.findAll();
        if(list == null){
            return new ResponseEntity<>("the table not inited", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("productId") Integer id) {
       Product product = productService.findById(id);
       if(product == null){
           return new ResponseEntity<>("this Id is not exist", HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if(product.getProductId()!=null&&productService.findById(product.getProductId())!=null){
            return new ResponseEntity("the product id is already exist",HttpStatus.BAD_REQUEST);
        }

        productService.create(product);
        return new ResponseEntity(product,HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam("productId") Integer id) {
        Product product = productService.findById(id);
        if(product == null){
            return new ResponseEntity("The product does not exist",HttpStatus.BAD_REQUEST);
        }
        productService.deleteById(id);
        return new ResponseEntity("The product deleted",HttpStatus.OK);
    }



    @GetMapping("/findAllDocumentation")
    public ResponseEntity<?>findAllDocumentation() {
        List<ProductDocumentation> list = documentationService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findDocumentationById")
    public ResponseEntity<?>   findDocumentationById(@RequestParam("productDocumentationId") Integer id) {
        ProductDocumentation documentation = documentationService.findById(id);
        if(documentation == null){
            return new ResponseEntity<>("this Id is not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(documentation, HttpStatus.OK);
    }

    @PostMapping("/addDocumentationToProduct")
    public ResponseEntity<?> addDocumentationToProduct(@RequestParam("productDocumentation") Product product,String documentationPath) {
        ProductDocumentation documentation= documentationService.addDocumentationToProduct(product,documentationPath);
        product.getProductDocumentations().add(documentation);
        return new ResponseEntity(documentation,HttpStatus.OK);
    }
}
