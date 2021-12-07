package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.*;

public class UserProductDTO {
    private Long id;
    private Integer quantity;
    private String productName;
    private double productPrice;
    private Integer points;
    private String disscountCode;
    private String address;
    private String productType;


    public UserProductDTO(UserProduct userProduct) {
        Product product = userProduct.getProduct();
        this.quantity = userProduct.getQuantity();
        this.id = product.getProductId();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.points = product.getPoints();
        this.disscountCode = product.getDisscountCode();
        this.address = product.getAddress();

        if (product instanceof Event) {
            this.productType = "EVENT";
        } else if (product instanceof Hotel) {
            this.productType = "HOTEL";
        } else if (product instanceof Ticket) {
            this.productType = "TICKET";
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getDisscountCode() {
        return disscountCode;
    }

    public void setDisscountCode(String disscountCode) {
        this.disscountCode = disscountCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}