package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRole;
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

    public Client(String firstName, String lastName, String email, String password, UserRole userRole, List<Product> cart) {
        super(firstName, lastName, email, password, userRole);
        this.cart = cart;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
