package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.enums.Pension;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sold_hotels")
public class ClientHotel extends UserProduct {

    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Integer nights;
    private Integer passangers;
    private Pension pension;

    public ClientHotel(){
    }

    public ClientHotel(User user, Product product) {
        super(user, product);
    }

    public ClientHotel(User user, Product product, LocalDateTime arrivalDate, LocalDateTime departureDate, Integer nights, Integer passangers) {
        super(user, product);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.nights = nights;
        this.passangers = passangers;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
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

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }
}
