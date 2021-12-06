package com.mindhub.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idsGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Long productId;
    protected Integer points;
    protected Double price;
    protected String disscountCode;
    protected String address;
    protected String name;
    protected Integer maxCapacity;
    protected Integer stock; //TODO Agregar
    protected String imgUrl;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id")
    protected User user;

    @OneToMany(mappedBy = "product")
    protected Set<UserProduct> productsOnCart = new HashSet();

    @OneToMany(mappedBy = "product")
    protected Set<UserProduct> allProducts = new HashSet();

    public Product(){

    }
    public Product(Integer points, Double price, String disscountCode, String address, String productName, Integer maxCapacity, Integer stock, String imgUrl) {
    }

    public Product(Integer points, Double price, String disscountCode, String address, User user, String name, Integer maxCapacity, Integer stock, String imgUrl) {
        this.points = points;
        this.price = price;
        this.disscountCode = disscountCode;
        this.address = address;
        this.user = user;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.stock = stock;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Set<UserProduct> getProductsOnCart() {
        return productsOnCart;
    }
    public void setProductsOnCart(Set<UserProduct> productsOnCart) {
        this.productsOnCart = productsOnCart;
    }

    public Set<UserProduct> getAllProducts() {
        return allProducts;
    }
    public void setAllProducts(Set<UserProduct> allProducts) {
        this.allProducts = allProducts;
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

    @Override
    public String toString() {
        return "ProductRepository{" +
                "productId=" + productId +
                ", points=" + points +
                ", price=" + price +
                ", disscountCode='" + disscountCode + '\'' +
                '}';
    }
}
