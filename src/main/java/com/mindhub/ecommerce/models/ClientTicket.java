package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.enums.TicketClass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sold_tickets")
public class ClientTicket extends UserProduct {

    private TicketClass ticketClass;

    public ClientTicket() {
    }

    public ClientTicket(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    public ClientTicket(User user, Product product, TicketClass ticketClass) {
        super(user, product);
        this.ticketClass = ticketClass;
    }

    public TicketClass getClase() {
        return ticketClass;
    }

    public void setClase(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }


    @Override
    public void setFinalPrice(Double finalPrice) {
        //TODO setear condiciones de final price para ticket

        switch (this.ticketClass) {
            case FIRST:
                finalPrice *= 1.5;
                break;
            case PREMIUM:
                finalPrice *= 2;
                break;
            case BUSINESS:
                finalPrice *= 1.2;
                break;
            case ECONOMIC:
                break;
        }

        super.setFinalPrice(finalPrice*quantity); // si es vip tiene un recargo del 20%

    }
}
