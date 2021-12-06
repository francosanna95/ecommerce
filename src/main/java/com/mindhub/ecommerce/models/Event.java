package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.dtos.EventDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "events")
public class Event extends Product  implements Serializable {


    private String artist;
    private boolean vip;

    public Event(){
        super();
    }

    public Event(EventDTO eventDTO) {
        super(eventDTO.getPoints(),eventDTO.getPrice(),eventDTO.getDisscountCode(),eventDTO.getAddress(),eventDTO.getProductName(),eventDTO.getDescription(), eventDTO.getStock(), eventDTO.getImgUrl());
        this.artist =eventDTO.getArtist();
        this.vip = eventDTO.isVip();
    }

    public Event(Integer points, Double price, String disscountCode, String address, User user, String name, String description, Integer stock, String imgUrl, String artist, boolean vip) {
        super(points, price, disscountCode, address, user, name, description, stock, imgUrl);
        this.artist = artist;
        this.vip = vip;
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

    @Override
    public void setPrice(Double price) {
        if (this.vip){
            super.setPrice(price *.5); /// ver que se le aumenta si es vip
        }else {
            super.setPrice(price);
        }
    }


}
