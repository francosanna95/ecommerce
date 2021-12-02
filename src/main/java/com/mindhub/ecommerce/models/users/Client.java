package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Product;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends User {

    private String firstName;
    private String lastName;


    public Client() {
    }

    public Client(String email, String password, UserRole userRole, String firstName, String lastName) {
        super(email, password, userRole);
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Client{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
