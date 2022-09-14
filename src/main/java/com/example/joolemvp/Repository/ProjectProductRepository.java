package com.example.joolemvp.Repository;

import com.example.joolemvp.Entity.ProjectProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProjectProductRepository extends JpaRepository<ProjectProduct,Integer> {


}
