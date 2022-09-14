package com.example.joolemvp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "project_product")
public class ProjectProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "project_product_id")
    private Integer project_product_id;

    @ManyToOne(targetEntity = Project.class,cascade =
            CascadeType.DETACH)
    //@JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(targetEntity = Product.class,cascade =
            CascadeType.DETACH)
   // @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="create_time")
    private String createTime;

    public Integer getProject_product_id() {
        return project_product_id;
    }

    public void setProject_product_id(Integer project_product_id) {
        this.project_product_id = project_product_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProjectProduct{" +
                "project_product_id=" + project_product_id +
                ", project=" + project +
                ", product=" + product +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}