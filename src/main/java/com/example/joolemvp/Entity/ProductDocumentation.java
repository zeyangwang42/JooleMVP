package com.example.joolemvp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "product_documentation")
public class ProductDocumentation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "documentation_id")
    private Integer documentationId;

    @Column(name = "documentation")
    private String documentationPath;

    @ManyToOne(targetEntity = Product.class,cascade = {
            CascadeType.DETACH})
    @JoinColumn(name ="product_id")
    private Product product;

    public Integer getDocumentationId() {
        return documentationId;
    }

    public void setDocumentationId(Integer documentationId) {
        this.documentationId = documentationId;
    }

    public String getDocumentationPath() {
        return documentationPath;
    }

    public void setDocumentationPath(String documentationPath) {
        this.documentationPath = documentationPath;
    }

    public Product getProductId() {
        return product;
    }

    public void setProductId(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductDocumentation{" +
                "documentationId=" + documentationId +
                ", documentationPath='" + documentationPath + '\'' +
                ", productId=" + product +
                '}';
    }
}