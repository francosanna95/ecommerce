package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.enums.Clase;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sold_tickets")
public class ClientTicket extends UserProduct{

    private Clase clase;
    private Integer passangers;

    public ClientTicket(){
    }

    public ClientTicket(Clase clase, Integer passangers) {
        this.clase = clase;
        this.passangers = passangers;
    }

    public ClientTicket(User user, Product product, Clase clase, Integer passangers) {
        super(user, product);
        this.clase = clase;
        this.passangers = passangers;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Integer getPassangers() {
        return passangers;
    }

    public void setPassangers(Integer passangers) {
        this.passangers = passangers;
    }
}
