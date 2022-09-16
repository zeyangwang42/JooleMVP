package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();

    public Product findById(Integer id);

    public Product create(Product product);

    public void deleteById(Integer id);
    public Collection<Product> findAllProductInTheProject(Project project);
    public List<Product> findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation, ProductAccessories accessories);
    List<Product> findByModelYearBetween(Integer startYear, Integer afterYear);
    List<Product> findByModelYearBefore(Integer afterYear);
    List<Product> findByModelYearAfter(Integer startYear);
    List<Product> findByAirflow(Integer start,Integer end);
    List<Product> findByPowerMax(Integer start,Integer end);
    /*List<Product> findProductByUseTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearBetweenAndAirflowBetweenAndPowerMaxBetweenAndSoundBetweenAndSweep_diameterBetweenBetweenAndHeight_minBetween(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation,
                                                                                                                                                                                                              ProductAccessories accessories, Integer modelYearStart, Integer modelYearEnd, Integer aitflowStart,
                                                                                                                                                                                                              Integer airflowEnd, Double powerStart, Double powerEnd, Integer soundStart, Integer soundEnd,
                                                                                                                                                                                                              Integer diameterStart,Integer diameterEnd,Integer heightStart,Integer heightEnd);
*/
}
