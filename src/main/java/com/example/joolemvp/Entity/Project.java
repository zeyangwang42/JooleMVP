package com.example.joolemvp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "projectId")
    private Integer projectId;

    @Column(name = "projectName")
    private String projectName;

    @Column(name="projectAddress")
    private String projectAddress;

    @Column(name="projectSize")
    private String projectSize;

    @Column(name="clientName")
    private String clientName;

    @OneToMany(targetEntity = ProjectProduct.class,cascade = CascadeType.REMOVE,mappedBy = "project")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectProduct> products=new HashSet<ProjectProduct>();

    @Column(name = "priceQuotes")
    private BigDecimal priceQuotes;

    @ManyToOne(targetEntity = User.class,cascade = {
            CascadeType.DETACH})
    @JoinColumn(name = "userId")
    private User user;


    @Column(name = "createTime")
    private Date createTime;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(String projectSize) {
        this.projectSize = projectSize;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Collection<ProjectProduct> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProjectProduct> products) {
        this.products = products;
    }

    public BigDecimal getPriceQuotes() {
        return priceQuotes;
    }

    public void setPriceQuotes(BigDecimal priceQuotes) {
        this.priceQuotes = priceQuotes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectAddress='" + projectAddress + '\'' +
                ", projectSize='" + projectSize + '\'' +
                ", clientName='" + clientName + '\'' +
                ", products=" + products +
                ", pricrQuotes=" + priceQuotes +
                ", user=" + user +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}