package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.models.users.Agency;
import com.mindhub.ecommerce.models.users.Client;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Product {

    @Id
    @GeneratedValue (strategy=GenerationType.TABLE , generator= "idsGenerator" )
    @TableGenerator (name= "idsGenerator" , table= "IdsGenerator" ,
            pkColumnName= "id" , pkColumnValue= "User" , valueColumnName= "employeeIds" )
    @Column (name =  "id" , unique = true  , nullable = false  )
    private Long productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
}
