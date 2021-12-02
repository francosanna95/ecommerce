package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;


@Entity
@Table(name = "agencies")
public class Agency extends User {

    private String fantasyName;
    private String address;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private Set<Product> availableProducts = new HashSet();

    public Agency() {
    }

    public Agency(String email, String password, UserRole userRole, String fantasyName, String address) {
        super(email, password, userRole);
        this.fantasyName = fantasyName;
        this.address = address;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(Set<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "fantasyName='" + fantasyName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
