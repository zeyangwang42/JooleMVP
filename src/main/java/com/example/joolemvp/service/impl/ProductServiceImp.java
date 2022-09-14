package com.example.joolemvp.service.impl;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import com.example.joolemvp.Repository.ProductRepository;
import com.example.joolemvp.Repository.ProjectProductRepository;
import com.example.joolemvp.service.ProductService;
import com.example.joolemvp.service.ProjectProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProjectProductService projectProductService;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product create(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation,
                       ProductAccessories accessories, Integer modelYear, Integer airflow, Double powerMin, Double powerMax,
                       Integer operatingVoltageMin, Integer operatingVoltageMax, Integer fanSpeedMin, Integer fanSpeedMax,
                       Integer fanSpeedNumbers, Integer sound, Integer sweep_diameter, Integer heightMax, Integer height_min,
                       Integer weight, String manufacturer, String series, String model) {
        Product product = new Product();
        product.setUseType(useType);
        product.setApplication(application);
        product.setMountingLocation(mountingLocation);
        product.setAccessories(accessories);
        product.setModelYear(modelYear);
        product.setAirflow(airflow);
        product.setPowerMin(powerMin);
        product.setPowerMax(powerMax);
        product.setOperatingVoltageMin(operatingVoltageMin);
        product.setOperatingVoltageMax(operatingVoltageMax);
        product.setFanSpeedMin(fanSpeedMin);
        product.setFanSpeedMax(fanSpeedMax);
        product.setFanSpeedNumbers(fanSpeedNumbers);
        product.setSound(sound);
        product.setSweep_diameter(sweep_diameter);
        product.setHeightMax(heightMax);
        product.setHeight_min(height_min);
        product.setWeight(weight);
        product.setManufacturer(manufacturer);
        product.setSeries(series);
        product.setModel(model);
        repository.save(product);
        return product;
    }

    @Override
    public void deleteById(Integer id) {

        Product product= repository.findById(id).orElse(null);
        if(product==null){
            return;
        }
     //   Collection<ProjectProduct> listNeedToDelete = product.getProjectProducts();
     //   for(ProjectProduct projectProduct : listNeedToDelete){
     //       projectProductService.deleteById(projectProduct.getProject_product_id());
      //  }
        repository.deleteById(id);

    }
    @Override
    public Collection<Product> findAllProductInTheProject(Project project){
        Collection<ProjectProduct> projectProducts = project.getProducts();
        Collection<Product> prodects = new HashSet<>();
        for(ProjectProduct projectProduct : projectProducts){
            prodects.add(projectProduct.getProduct());
        }
        return prodects;
    }

    @Override
    public List<Product> findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation, ProductAccessories accessories) {
        return repository.findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(useType, application, mountingLocation,  accessories).orElse(null);
    }

    @Override
    public List<Product> findByModelYearBetween(Integer startYear, Integer afterYear) {
        return repository.findByModelYearBetween(startYear,afterYear).orElse(null);
    }

    @Override
    public List<Product> findByModelYearBefore(Integer afterYear) {
        return repository.findByModelYearBefore(afterYear).orElse(null);
    }

    @Override
    public List<Product> findByModelYearAfter(Integer startYear) {
        return repository.findByModelYearAfter(startYear).orElse(null);
    }

    @Override
    public List<Product> findByAirflow(Integer start,Integer end) {
        return repository.findByAirflowBetween(start,end).orElse(null);
    }

    @Override
    public List<Product> findByPowerMax(Integer start, Integer end) {
        return repository.findByPowerMaxBetween(start,end).orElse(null);
    }

    /**@Override
    public List<Product> findProductByUseTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearBetweenAndAirflowBetweenAndPowerMaxBetweenAndSoundBetweenAndSweep_diameterBetweenBetweenAndHeight_minBetween(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation, ProductAccessories accessories, Integer modelYearStart, Integer modelYearEnd, Integer aitflowStart, Integer airflowEnd, Double powerStart, Double powerEnd, Integer soundStart, Integer soundEnd, Integer diameterStart, Integer diameterEnd, Integer heightStart, Integer heightEnd) {
        return null;
    }*/
}
