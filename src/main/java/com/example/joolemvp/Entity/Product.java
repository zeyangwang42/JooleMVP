package com.example.joolemvp.Entity;

import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;



    @JsonIgnore
    @OneToMany(targetEntity = ProjectProduct.class, cascade = CascadeType.REMOVE,mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectProduct> projectProducts = new HashSet<ProjectProduct>();

    @JsonIgnore
    @OneToMany(targetEntity=ProductDocumentation.class,cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProductDocumentation> productDocumentations=new HashSet<ProductDocumentation>();

    @Enumerated(EnumType.STRING)
    @Column(name = "use_type")
    private ProductUseType useType;

    @Enumerated(EnumType.STRING)
    @Column(name="application")
    private ProductApplication application;

    @Enumerated(EnumType.STRING)
    @Column(name="mounting_location")
    private ProductMountingLocation mountingLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "Accessories")
    private ProductAccessories accessories;

    @Column(name="model_year")
    private Integer modelYear;

    @Column(name="airflow")
    private Integer airflow;

    @Column(name="power_min")
    private Double powerMin;

    @Column(name="power_max")
    private Double powerMax;

    @Column(name="operating_voltage_min")
    private Integer operatingVoltageMin;

    @Column(name="operating_voltage_max")
    private Integer operatingVoltageMax;

    @Column(name="fan_speed_min")
    private Integer fanSpeedMin;

    @Column(name="fan_speed_max")
    private Integer fanSpeedMax;

    @Column(name="fan_speed_number")
    private Integer fanSpeedNumbers;

    @Column(name="max_speed_sound")
    private Integer sound;

    @Column(name="fan_sweep_diameter")
    private Integer sweep_diameter;

    @Column(name="height_max")
    private Integer heightMax;

    @Column(name="height_min")
    private Integer height_min;

    @Column(name="weight")
    private Integer weight;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="series")
    private String series;

    @Column(name = "model")
    private String model;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Collection<ProjectProduct> getProjectProducts() {
        return projectProducts;
    }

    public void setProjectProducts(Collection<ProjectProduct> projectProducts) {
        this.projectProducts = projectProducts;
    }

    public Collection<ProductDocumentation> getProductDocumentations() {
        return productDocumentations;
    }

    public void setProductDocumentations(Collection<ProductDocumentation> productDocumentations) {
        this.productDocumentations = productDocumentations;
    }

    public ProductUseType getUseType() {
        return useType;
    }

    public void setUseType(ProductUseType useType) {
        this.useType = useType;
    }

    public ProductApplication getApplication() {
        return application;
    }

    public void setApplication(ProductApplication application) {
        this.application = application;
    }

    public ProductMountingLocation getMountingLocation() {
        return mountingLocation;
    }

    public void setMountingLocation(ProductMountingLocation mountingLocation) {
        this.mountingLocation = mountingLocation;
    }

    public ProductAccessories getAccessories() {
        return accessories;
    }

    public void setAccessories(ProductAccessories accessories) {
        this.accessories = accessories;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getAirflow() {
        return airflow;
    }

    public void setAirflow(Integer airflow) {
        this.airflow = airflow;
    }

    public Double getPowerMin() {
        return powerMin;
    }

    public void setPowerMin(Double powerMin) {
        this.powerMin = powerMin;
    }

    public Double getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(Double powerMax) {
        this.powerMax = powerMax;
    }

    public Integer getOperatingVoltageMin() {
        return operatingVoltageMin;
    }

    public void setOperatingVoltageMin(Integer operatingVoltageMin) {
        this.operatingVoltageMin = operatingVoltageMin;
    }

    public Integer getOperatingVoltageMax() {
        return operatingVoltageMax;
    }

    public void setOperatingVoltageMax(Integer operatingVoltageMax) {
        this.operatingVoltageMax = operatingVoltageMax;
    }

    public Integer getFanSpeedMin() {
        return fanSpeedMin;
    }

    public void setFanSpeedMin(Integer fanSpeedMin) {
        this.fanSpeedMin = fanSpeedMin;
    }

    public Integer getFanSpeedMax() {
        return fanSpeedMax;
    }

    public void setFanSpeedMax(Integer fanSpeedMax) {
        this.fanSpeedMax = fanSpeedMax;
    }

    public Integer getFanSpeedNumbers() {
        return fanSpeedNumbers;
    }

    public void setFanSpeedNumbers(Integer fanSpeedNumbers) {
        this.fanSpeedNumbers = fanSpeedNumbers;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Integer getSweep_diameter() {
        return sweep_diameter;
    }

    public void setSweep_diameter(Integer sweep_diameter) {
        this.sweep_diameter = sweep_diameter;
    }

    public Integer getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(Integer heightMax) {
        this.heightMax = heightMax;
    }

    public Integer getHeight_min() {
        return height_min;
    }

    public void setHeight_min(Integer height_min) {
        this.height_min = height_min;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", projectProducts=" + projectProducts +
                ", productDocumentations=" + productDocumentations +
                ", useType=" + useType +
                ", application=" + application +
                ", mountingLocation=" + mountingLocation +
                ", accessories=" + accessories +
                ", modelYear='" + modelYear + '\'' +
                ", airflow=" + airflow +
                ", powerMin=" + powerMin +
                ", powerMax=" + powerMax +
                ", operatingVoltageMin=" + operatingVoltageMin +
                ", operatingVoltageMax=" + operatingVoltageMax +
                ", fanSpeedMin=" + fanSpeedMin +
                ", fanSpeedMax=" + fanSpeedMax +
                ", fanSpeedNumbers=" + fanSpeedNumbers +
                ", sound=" + sound +
                ", sweep_diameter=" + sweep_diameter +
                ", heightMax=" + heightMax +
                ", height_min=" + height_min +
                ", weight=" + weight +
                ", manufacturer='" + manufacturer + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}