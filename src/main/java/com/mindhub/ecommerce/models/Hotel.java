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
    private Integer availableRooms;
    private Pension offeredPension;

    public Hotel(){
        super();

    }

    public Hotel(HotelDTO hotelDTO) {
        super(hotelDTO.getPoints(),hotelDTO.getPrice(),hotelDTO.getDisscountCode(),hotelDTO.getAddress(),hotelDTO.getProductName(),hotelDTO.getMaxCapacity(),hotelDTO.getStock(),hotelDTO.getImgUrl());
        this.parking = hotelDTO.getParking();
        this.concierge = hotelDTO.getConcierge();
        this.availableRooms = hotelDTO.getAvailableRooms();
        this.offeredPension = hotelDTO.getOfferedPension();
    }

    public Hotel(Integer points, Double price, String disscountCode, String address, User user, String name, Integer maxCapacity, Integer stock, String imgUrl, Boolean parking, Boolean concierge, Integer availableRooms, Pension offeredPension) {
        super(points, price, disscountCode, address, user, name, maxCapacity, stock, imgUrl);
        this.parking = parking;
        this.concierge = concierge;
        this.availableRooms = availableRooms;
        this.offeredPension = offeredPension;
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

    public Integer getAvailableRooms() {
        return availableRooms;
    }
    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }

    public Pension getOfferedPension() {
        return offeredPension;
    }
    public void setOfferedPension(Pension offeredPension) {
        this.offeredPension = offeredPension;
    }

//    public void addClientHotel(ClientHotel clientHotel){
//        clientHotel.setProduct(this);
//        productsOnCart.add(clientHotel);
//    }


}
