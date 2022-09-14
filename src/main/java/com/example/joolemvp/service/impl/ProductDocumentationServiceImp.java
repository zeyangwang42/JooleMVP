package com.example.joolemvp.service.impl;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.ProductDocumentation;
import com.example.joolemvp.Repository.ProductDocumentationRepository;
import com.example.joolemvp.service.ProductDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProductDocumentationServiceImp implements ProductDocumentationService {
    @Autowired
    private ProductDocumentationRepository repository;

    @Override
    public List<ProductDocumentation> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductDocumentation findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProductDocumentation addDocumentationToProduct(Product product,String documentationPath) {
        ProductDocumentation productDocumentation = new ProductDocumentation();
        productDocumentation.setDocumentationPath(documentationPath);
        product.getProductDocumentations().add(productDocumentation);
        productDocumentation.setProductId(product);
        repository.save(productDocumentation);
        return productDocumentation;
    }

    @Override
    public void deleteDocumentationFromProduct(ProductDocumentation productDocumentation) {
        Product product= productDocumentation.getProductId();
        if(product==null){
            return;
        }
        int id = productDocumentation.getDocumentationId();
        product.getProductDocumentations().remove(productDocumentation);
        repository.deleteById(id);
    }
}
