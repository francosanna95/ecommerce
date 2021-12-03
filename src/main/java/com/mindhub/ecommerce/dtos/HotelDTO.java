package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.models.ClientProduct;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class HotelDTO {

    private Integer nights;
    private Integer passengers;
    private String room;
    private Pension pension;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Set<ClientProduct> clientProducts = new HashSet();

    public HotelDTO() {}
    public HotelDTO(Hotel hotel) {
        this.clientProducts=hotel.getClientProducts();
        this.nights=hotel.getNights();
        this.passengers=hotel.getPassengers();
        this.room=hotel.getRoom();
        this.pension=hotel.getPension();
        this.arrivalDate=hotel.getArrivalDate();
        this.departureDate=hotel.getDepartureDate();
    }

    public Integer getNights() {return nights;}
    public void setNights(Integer nights) {this.nights = nights;}

    public Integer getPassengers() {return passengers;}
    public void setPassengers(Integer passengers) {this.passengers = passengers;}

    public String getRoom() {return room;}
    public void setRoom(String room) {this.room = room;}

    public Pension getPension() {return pension;}
    public void setPension(Pension pension) {this.pension = pension;}

    public LocalDateTime getArrivalDate() {return arrivalDate;}
    public void setArrivalDate(LocalDateTime arrivalDate) {this.arrivalDate = arrivalDate;}

    public LocalDateTime getDepartureDate() {return departureDate;}
    public void setDepartureDate(LocalDateTime departureDate) {this.departureDate = departureDate;}

    public Set<ClientProduct> getClientProducts() {return clientProducts;}
    public void setClientProducts(Set<ClientProduct> clientProducts) {this.clientProducts = clientProducts;}
}
