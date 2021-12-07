package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.dtos.HotelDTO;
import com.mindhub.ecommerce.enums.Pension;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel extends Product {

    private Boolean parking;
    private Boolean concierge;

    public Hotel(){
        super();

    }

    public Hotel(HotelDTO hotelDTO) {
        super(hotelDTO.getPoints(),hotelDTO.getPrice(),hotelDTO.getDisscountCode(),hotelDTO.getAddress(),hotelDTO.getProductName(),hotelDTO.getDescription(),hotelDTO.getStock(),hotelDTO.getImgUrl());
        this.parking = hotelDTO.getParking();
        this.concierge = hotelDTO.getConcierge();

    }

    public Hotel(Integer points, Double price, String disscountCode, String address, User user, String name, String description, Integer stock, String imgUrl, Boolean parking, Boolean concierge, Integer availableRooms, Pension offeredPension) {
        super(points, price, disscountCode, address, user, name, description, stock, imgUrl);
        this.parking = parking;
        this.concierge = concierge;

    }

    public Boolean getParking() {
        return parking;
    }
    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getConcierge() {
        return concierge;
    }
    public void setConcierge(Boolean concierge) {
        this.concierge = concierge;
    }






}
