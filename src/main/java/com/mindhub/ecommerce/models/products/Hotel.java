package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.models.users.Agency;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel extends Product{

    public String address;
    public Integer nights;
    public Integer passangers;
    public String room;

    public Hotel() {
    }


    public Hotel(Integer points, double price, String disscountCode, Agency agency, String address, Integer nights, Integer passangers, String room, List<String> preferences) {
        super(points, price, disscountCode, agency);
        this.address = address;
        this.nights = nights;
        this.passangers = passangers;
        this.room = room;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public Integer getPassangers() {
        return passangers;
    }

    public void setPassangers(Integer passangers) {
        this.passangers = passangers;
    }


    @Override
    public void setPrice(double price) {
        super.setPrice(price * this.nights * this.passangers);
    }
}
