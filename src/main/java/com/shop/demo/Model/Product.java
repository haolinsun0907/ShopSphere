package com.shop.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.GenericGenerator;


import java.time.OffsetDateTime;
import java.time.ZoneId;
@Entity
@Table(name = "products")
public class Product {
    @Id

    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "product_id")
    private String productId;
    @NotBlank
    private String productName;
    @NotBlank
    private String productDescription;

    @Min(value = 0, message = "price must be greater than 0")
    private double productPrice;

    @Min(value = 0, message = "quantity must be greater than 0")
    private double productStockQuantity;
    @NotBlank
    private String productImageUrl;

    private String CreateAt=OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();

    private String UpdateAt=OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();

    public Product(String productId, String productName, String productDescription, double productPrice, double productStockQuantity, String productImageUrl, String createAt, String updateAt) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStockQuantity = productStockQuantity;
        this.productImageUrl = productImageUrl;
        this.CreateAt =createAt;
        this.UpdateAt = updateAt;
    }

    public Product() {

    }

    public String getProductId() {
        return productId;
    }

//    public void setProductId(String productId) {
//        this.productId = productId;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductStockQuantity() {
        return productStockQuantity;
    }

    public void setProductStockQuantity(double productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String createAt) {
        CreateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
    }

    public String getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt() {
        UpdateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
    }
}
