package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRol;
import com.mindhub.ecommerce.models.products.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends User {

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Product> cart;

    public Client() {
    }

    public Client(List<Product> cart) {
        this.cart = cart;
    }

    public Client(String firstName, String lastName, String email, String password, UserRol userRol, List<Product> cart) {
        super(firstName, lastName, email, password, userRol);
        this.cart = cart;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
