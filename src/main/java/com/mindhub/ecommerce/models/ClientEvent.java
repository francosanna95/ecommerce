package com.mindhub.ecommerce.models;

import javax.persistence.*;

@Entity
@Table(name="sold_events")
public class ClientEvent extends UserProduct {

    private Boolean isVip;

    public ClientEvent(){

    }
    public ClientEvent(Boolean isVip) {
        this.isVip = isVip;
    }

    public ClientEvent(User user, Product product, Boolean isVip) {
        super(user, product);
        this.isVip = isVip;


    }

    public Boolean getVip() {
        return isVip;
    }
    public void setVip(Boolean vip) {
        isVip = vip;
    }


    @Override
    public void setFinalPrice(Double finalPrice) {
        if (this.isVip){
            super.setFinalPrice(finalPrice*1.2*quantity); // si es vip tiene un recargo del 20%
        } else {
            super.setFinalPrice(finalPrice*quantity);
        }

    }
}
