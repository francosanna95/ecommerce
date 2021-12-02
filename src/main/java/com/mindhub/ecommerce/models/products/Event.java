package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.models.users.Agency;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends Product {

    private String artist;
    private String location;

    public Event() {
    }

    public Event(String artist, String location) {
        this.artist = artist;
        this.location = location;
    }

    public Event(Integer points, double price, String disscountCode, Agency agency, String artist, String location) {
        super(points, price, disscountCode, agency);
        this.artist = artist;
        this.location = location;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
