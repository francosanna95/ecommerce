package com.mindhub.ecommerce.models;


import com.mindhub.ecommerce.enums.UserRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


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
    private String fantasyName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserProduct> currentCart = new LinkedList<>();

    @OneToMany(mappedBy = "userHistory", fetch = FetchType.EAGER)
    private List<UserProduct> shoppingHistory = new LinkedList<>();

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public User(String email, String password, String address, String bankAccountNumber, String firstName, String lastName, String imgUrl, String fantasyName, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.bankAccountNumber = bankAccountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
        this.fantasyName = fantasyName;
        this.userRole = userRole;
    }

    public void addClientProductToCurrentCart(UserProduct userProduct) {
        userProduct.setUser(this);
        this.currentCart.add(userProduct);
    }

    public void addClientProductToHistoryCart(UserProduct userProduct) {
        userProduct.setUserHistory(this);
        this.shoppingHistory.add(userProduct);
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

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public List<UserProduct> getCurrentCart() {
        return currentCart;
    }

    public void setCurrentCart(List<UserProduct> currentCart) {
        this.currentCart = currentCart;
    }

    public List<UserProduct> getShoppingHistory() {
        return shoppingHistory;
    }

    public void setShoppingHistory(List<UserProduct> shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
