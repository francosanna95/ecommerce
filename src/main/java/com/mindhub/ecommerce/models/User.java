package com.mindhub.ecommerce.models;


import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.ClientProduct;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String email;
    private String password;
    private String address;
    private String bankAccountNumber;
    private String firstName;
    private String lastName;
    private String imgUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ClientProduct> cart = new HashSet<>();

    private String fantasyName;
    //   @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    //   private Set<ClientProduct> historyCart = new HashSet();

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {}

    public User(String firstName, String lastName,String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public void addClientProduct(ClientProduct clientProduct){
        this.cart.add(clientProduct);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getImgUrl() {return imgUrl;}
    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}

    public Set<ClientProduct> getCart() {return cart;}
    public void setCart(Set<ClientProduct> cart) {this.cart = cart;}

    public String getFantasyName() {return fantasyName;}
    public void setFantasyName(String fantasyName) {this.fantasyName = fantasyName;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
