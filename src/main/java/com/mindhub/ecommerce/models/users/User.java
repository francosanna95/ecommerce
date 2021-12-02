package com.mindhub.ecommerce.models.users;


import com.mindhub.ecommerce.enums.UserRol;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User  implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.TABLE , generator= "idsGenerator" )
    @TableGenerator (name= "idsGenerator" , table= "IdsGenerator" ,
            pkColumnName= "id" , pkColumnValue= "User" , valueColumnName= "employeeIds" )
    @Column (name =  "id" , unique = true  , nullable = false  )
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRol userRol;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, UserRol userRol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRol = userRol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserRol getUserRol() {
        return userRol;
    }

    public void setUserRol(UserRol userRol) {
        this.userRol = userRol;
    }
}
