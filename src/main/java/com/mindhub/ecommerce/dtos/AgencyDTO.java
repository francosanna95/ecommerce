package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.products.Product;
import com.mindhub.ecommerce.models.users.Agency;

import java.util.HashSet;
import java.util.Set;

public class AgencyDTO {

    private String fantasyName;
    private Set<Product> availableProducts = new HashSet();
    private Long id;
    private String email;
    private String password;
    private String address;
    private String bankAccountNumber;

    public AgencyDTO() {}
    public AgencyDTO(Agency agency) {
        this.id=agency.getId();
        this.email=agency.getEmail();
        this.password=agency.getPassword();
        this.address=agency.getAddress();
        this.bankAccountNumber=agency.getBankAccountNumber();
      //  this.availableProducts=agency.getAvailableProducts();
        this.fantasyName=agency.getFantasyName();
    }

    public String getFantasyName() {return fantasyName;}
    public void setFantasyName(String fantasyName) {this.fantasyName = fantasyName;}

    public Set<Product> getAvailableProducts() {return availableProducts;}
    public void setAvailableProducts(Set<Product> availableProducts) {this.availableProducts = availableProducts;}

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
}
