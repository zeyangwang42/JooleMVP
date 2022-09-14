package com.example.joolemvp.Repository;

import com.example.joolemvp.Entity.ProductDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductDocumentationRepository extends JpaRepository<ProductDocumentation,Integer> {
}
