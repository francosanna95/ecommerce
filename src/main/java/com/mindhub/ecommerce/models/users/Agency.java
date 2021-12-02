package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Product;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="agencies")
public class Agency extends User {

    private String address;
    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<Product> availableProducts;

    public Agency() {
    }

    public Agency(String firstName, String lastName, String email, String password, UserRole userRole, String address, List<Product> availableProducts) {
        super(firstName, lastName, email, password, userRole);
        this.address = address;
        this.availableProducts = availableProducts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }
}
