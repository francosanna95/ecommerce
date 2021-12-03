package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.products.Event;
import com.mindhub.ecommerce.models.ClientProduct;

import java.util.HashSet;
import java.util.Set;

public class EventDTO {


    private Set<ClientProduct> clientProducts = new HashSet();
    private String artist;
    private Integer maxCapacity;
    private boolean vip;

    public EventDTO() {
    }
    public EventDTO(Event event) {

        this.clientProducts=event.getClientProducts();
        this.artist=event.getArtist();
        this.maxCapacity=event.getMaxCapacity();
        this.vip=event.isVip();
    }

    public Set<ClientProduct> getClientProducts() {return clientProducts;}
    public void setClientProducts(Set<ClientProduct> clientProducts) {this.clientProducts = clientProducts;}

    public String getArtist() {return artist;}
    public void setArtist(String artist) {this.artist = artist;}

    public Integer getMaxCapacity() {return maxCapacity;}
    public void setMaxCapacity(Integer maxCapacity) {this.maxCapacity = maxCapacity;}

    public boolean isVip() {return vip;}
    public void setVip(boolean vip) {this.vip = vip;}
}
