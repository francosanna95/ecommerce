package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Event;
import com.mindhub.ecommerce.models.Product;

public class EventDTO extends ProductDTO {

    private String artist;
    private Integer maxCapacity;
    private boolean vip;


    public EventDTO() {
    }

    public EventDTO(Product product) {
        super(product);
        Event event = (Event) product;
        this.artist = event.getArtist();
        this.maxCapacity = event.getMaxCapacity();
        this.vip = event.isVip();

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
