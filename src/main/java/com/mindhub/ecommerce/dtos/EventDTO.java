package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Event;
import com.mindhub.ecommerce.models.Product;

public class EventDTO extends ProductDTO {

    //si volamos esta clase? q directamente reciba un ProductoDTO...
    //la dejo por si las moscas

    private String artist;
    private boolean vip;


    public EventDTO() {
    }

    public EventDTO(Product product) {
        super(product);
        Event event = (Event) product;
        this.artist = event.getArtist();
        this.vip = event.isVip();

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
