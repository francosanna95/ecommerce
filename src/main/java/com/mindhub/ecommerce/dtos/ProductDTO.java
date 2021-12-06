package com.mindhub.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.ecommerce.models.Event;
import com.mindhub.ecommerce.models.Hotel;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;

public class ProductDTO {
    private Long productId;
    private String productType;
    private String productName;
    private Integer points;
    private Double price;
    private String disscountCode;
    private String address;
    private UserDTO agency;
    private Integer maxCapacity;
    private Integer stock;
    private String imgUrl;


    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.productName = product.getName();
        this.productId = product.getProductId();
        this.points = product.getPoints();
        this.price = product.getPrice();
        this.disscountCode = product.getDisscountCode();
        this.address = product.getAddress();
        this.agency = new UserDTO(product.getUser());
        this.stock = product.getStock();
        this.imgUrl = product.getImgUrl();

        if (product instanceof Event) {
            this.productType = "EVENT";
        } else if (product instanceof Hotel) {
            this.productType = "HOTEL";
        } else if (product instanceof Ticket) {
            this.productType = "TICKET";
        }
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    @JsonIgnore
    public UserDTO getAgency() {
        return agency;
    }

    public void setAgency(UserDTO agency) {
        this.agency = agency;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
