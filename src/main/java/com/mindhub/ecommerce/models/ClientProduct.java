package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.models.products.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    //VENTA
    public ClientProduct() {}

    public ClientProduct(User user, Product product) {
        this.user=user;
        this.product=product;
    }

    public long getId() {return id;}

    public User getUser() {return user;}
    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
}
