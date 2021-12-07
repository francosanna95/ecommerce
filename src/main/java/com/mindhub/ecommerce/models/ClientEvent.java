package com.mindhub.ecommerce.models;

import javax.persistence.*;

@Entity
@Table(name="sold_events")
public class ClientEvent extends UserProduct {

    private Boolean isVip;
    private Integer attendants; // cantidad de tickets q compra

    public ClientEvent(){

    }
    public ClientEvent(Boolean isVip, Integer attendants) {
        this.isVip = isVip;
        this.attendants = attendants;
    }

    public ClientEvent(User user, Product product, Boolean isVip, Integer attendants) {
        super(user, product);
        this.isVip = isVip;
        this.attendants = attendants;
    }

    public Boolean getVip() {
        return isVip;
    }
    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public Integer getAttendants() {
        return attendants;
    }
    public void setAttendants(Integer attendants) {
        this.attendants = attendants;
    }
}