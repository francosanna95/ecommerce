package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Product;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "clients")
public class Client extends User {

    private String firstName;
    private String lastName;
    private String imgUrl;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Product> cart = new HashSet();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Product> historyCart = new HashSet();

    public Client() {
    }

    public Client(String email, String password, UserRole userRole, String firstName, String lastName, String imgUrl, String address, String bankAccountNumber) {
        super(email, password, userRole, address, bankAccountNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Product> getCart() {
        return cart;
    }

    public void setCart(Set<Product> cart) {
        this.cart = cart;
    }

    public Set<Product> getHistoryCart() {
        return historyCart;
    }

    public void setHistoryCart(Set<Product> historyCart) {
        this.historyCart = historyCart;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
