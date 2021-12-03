package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.models.users.Agency;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends Product {

    private String artist;
    private Integer maxCapacity;
    private boolean vip;



    public Event() {
    }

    public Event(Integer points, Double price, String disscountCode, String address, Agency agency, String artist, Integer maxCapacity, boolean vip) {
        super(points, price, disscountCode, address, agency);
        this.artist = artist;
        this.maxCapacity = maxCapacity;
        this.vip = vip;
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

    @Override
    public void setPrice(Double price) {
        if (this.vip){
            super.setPrice(price *.5); /// ver que se le aumenta si es vip
        }else {
            super.setPrice(price);
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "artist='" + artist + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", vip=" + vip +
                '}';
    }
}
