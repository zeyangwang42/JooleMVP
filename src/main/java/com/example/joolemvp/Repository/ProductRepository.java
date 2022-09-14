package com.example.joolemvp.Repository;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findById(Integer integer);

    Optional<List<Product>> findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation, ProductAccessories accessories);

    Optional<List<Product>> findByModelYearBetween(Integer startYear, Integer afterYear);
    Optional<List<Product>> findByModelYearBefore(Integer afterYear);
    Optional<List<Product>> findByModelYearAfter(Integer startYear);

    /**Optional<List<Product>> findProductByUseTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearBetweenAndAirflowBetweenAndOperatingVoltageBetweenAndFanSpeedBetweenAndFanSpeedNumbersBetweenAndSoundBetweenAndSweep_diameterBetweenAndHeightBetween(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation,
                                                   ProductAccessories accessories,Integer modelYearFrom,Integer modelYearEnd,Integer airflowFrom,Integer airflowEnd,Double powerMinFrom,Double powerMinEnd,Double powerMaxFrom,Double powerMaxEnd,
                                                   Integer operatingVoltageFrom,Integer operatingVoltageEnd,Integer fanSpeedFrom,Integer fanSpeedEnd
                                                   Integer fanSpeedNumbersFrom, Integer fanSpeedNumbersEnd,Integer soundFrom,Integer soundEnd,Integer sweepDiameterFrom,Integer sweepDiameterEnd, Integer heightFrom, Integer heightEnd);
    */
    Optional<List<Product>> findByAirflowBetween(Integer start,Integer End);
    Optional<List<Product>> findByPowerMaxBetween(Integer start,Integer End);
    /*Optional<List<Product>> findProductByUseTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearBetweenAndAirflowBetweenAndPowerMaxBetweenAndSoundBetweenAndSweep_diameterBetweenBetweenAndHeight_minBetween(ProductUseType useType, ProductApplication application, ProductMountingLocation mountingLocation,
                                                                                                                                                                                                                 ProductAccessories accessories, Integer modelYearStart, Integer modelYearEnd, Integer aitflowStart,
                                                                                                                                                                                                                 Integer airflowEnd, Double powerStart, Double powerEnd, Integer soundStart, Integer soundEnd,
                                                                                                                                                                                                                 Integer diameterStart,Integer diameterEnd,Integer heightStart,Integer heightEnd);
*/
}
