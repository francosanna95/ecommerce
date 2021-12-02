package com.mindhub.ecommerce.models.users;


import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//Single Table crea una tabla Users con todos los elementos, TABLE_PER_CLASS crea una tabla compartida
//Podes ver la diferencia en H2-Console entre como se comporta la persistencia en User y en Products
public abstract class User  implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.TABLE , generator= "idsGenerator" )
    @TableGenerator (name= "idsGenerator" , table= "IdsGenerator" ,
            pkColumnName= "id" , pkColumnValue= "User" , valueColumnName= "employeeIds" )
    @Column (name =  "id" , unique = true  , nullable = false  )
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
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
