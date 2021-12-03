package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;


@Entity
//@Table(name = "agencies")
public class Agency extends User {

    private String fantasyName;
//    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
//    private Set<Product> availableProducts = new HashSet();

    public Agency() {
    }

    public Agency(String email, String password, UserRole userRole, String fantasyName, String address, String bankAccountNumber) {
        super(email, password, userRole,address,bankAccountNumber);
        this.fantasyName = fantasyName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }



 //   public Set<Product> getAvailableProducts() {
 //       return availableProducts;
 //   }

 //   public void setAvailableProducts(Set<Product> availableProducts) {
 //       this.availableProducts = availableProducts;
 //   }

    @Override
    public String toString() {
        return "Agency{" +
                "fantasyName='" + fantasyName + '\'' +
                '}';
    }
}
