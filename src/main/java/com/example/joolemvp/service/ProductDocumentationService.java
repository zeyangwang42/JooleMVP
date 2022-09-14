package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.ProductDocumentation;

import java.util.List;
import java.util.Optional;

public interface ProductDocumentationService {
    public List<ProductDocumentation> findAll();

    public ProductDocumentation findById(Integer id);

    public ProductDocumentation addDocumentationToProduct(Product product, String documentationPath);

    public void deleteDocumentationFromProduct(ProductDocumentation productDocumentation);
}
