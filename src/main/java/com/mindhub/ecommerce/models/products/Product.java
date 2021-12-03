package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.models.users.Agency;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Product implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.TABLE , generator= "idsGenerator" )
    @TableGenerator (name= "idsGenerator.product" , table= "ProducstIdsGenerator" ,
            pkColumnName= "id" , pkColumnValue= "Product" , valueColumnName= "productsIds" )
    @Column (name =  "id" , unique = true  , nullable = false  )
    private Long productId;
    private Integer points;
    private Double price;
    private String disscountCode;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id")
    private Agency agency;

    //TODO:Revisar si nos conviene hacer una tabla intermedia productClient por la relación ManyToMany
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "client_id")
//    private Client productClient;


    public Product() {
    }

    public Product(Integer points, double price, String disscountCode, Agency agency) {
        this.points = points;
        this.price = price;
        this.disscountCode = disscountCode;
        this.agency = agency;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDisscountCode() {
        return disscountCode;
    }

    public void setDisscountCode(String disscountCode) {
        this.disscountCode = disscountCode;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", points=" + points +
                ", price=" + price +
                ", disscountCode='" + disscountCode + '\'' +
                '}';
    }
}
