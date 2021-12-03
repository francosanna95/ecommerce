package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.users.ClientProducts;
import com.mindhub.ecommerce.models.users.User;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String imgUrl;
    private Set<ClientProducts> cart = new HashSet();
    private Set<ClientProducts> historyCart = new HashSet();
    private Long id;
    private String email;
    private String password;
    private String address;
    private String bankAccountNumber;


    public UserDTO() {}
    public UserDTO(User client) {
        this.firstName=client.getFirstName();
        this.lastName=client.getLastName();
        this.imgUrl=client.getImgUrl();
        this.cart=client.getCart();
        //       this.historyCart=client.getHistoryCart();
        this.email=client.getEmail();
        this.id=client.getId();
        this.password=client.getPassword();
        this.address=client.getAddress();
        this.bankAccountNumber=client.getBankAccountNumber();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getBankAccountNumber() {return bankAccountNumber;}
    public void setBankAccountNumber(String bankAccountNumber) {this.bankAccountNumber = bankAccountNumber;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getImgUrl() {return imgUrl;}
    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}

    public Set<ClientProducts> getCart() {return cart;}
    public void setCart(Set<ClientProducts> cart) {this.cart = cart;}

    public Set<ClientProducts> getHistoryCart() {return historyCart;}
    public void setHistoryCart(Set<ClientProducts> historyCart) {this.historyCart = historyCart;}
}
