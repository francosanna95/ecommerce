package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.enums.TicketClass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sold_tickets")
public class ClientTicket extends UserProduct{

    private TicketClass ticketClass;
    private Integer passangers;

    public ClientTicket(){
    }

    public ClientTicket(TicketClass ticketClass, Integer passangers) {
        this.ticketClass = ticketClass;
        this.passangers = passangers;
    }

    public ClientTicket(User user, Product product, TicketClass ticketClass, Integer passangers) {
        super(user, product);
        this.ticketClass = ticketClass;
        this.quantity = passangers;
    }

    public TicketClass getClase() {
        return ticketClass;
    }

    public void setClase(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    public Integer getPassangers() {
        return passangers;
    }

    public void setPassangers(Integer passangers) {
        this.passangers = passangers;
    }


    @Override
    public void setFinalPrice(Double finalPrice) {
        //TODO setear condiciones de final price para ticket
        if (this.ticketClass.equals(TicketClass.FIRST)){
            finalPrice *= 1.2;
        }
        super.setFinalPrice(finalPrice); // si es vip tiene un recargo del 20%

    }
}
