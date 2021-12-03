package com.mindhub.ecommerce.models.users;


import com.mindhub.ecommerce.enums.UserRole;
import javax.persistence.*;


@Entity
public abstract class User {

    @Id
    @GeneratedValue (strategy=GenerationType.TABLE , generator= "idsGenerator" )
    @TableGenerator (name= "idsGenerator.users" , table= "UserIdsGenerator" ,
            pkColumnName= "id" , pkColumnValue= "User" , valueColumnName= "userIds" )
    @Column (name =  "id" , unique = true  , nullable = false  )
    private Long id;
    private String email;
    private String password;
    private String address;
    private String bankAccountNumber;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(String email, String password, UserRole userRole,String address,String bankAccountNumber) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.address = address;
        this.bankAccountNumber = bankAccountNumber;
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
