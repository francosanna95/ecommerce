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
    private Pension pension;

    public ClientHotel() {
    }

    public ClientHotel(User user, Product product) {
        super(user, product);
    }

    public ClientHotel(User user, Product product, LocalDateTime arrivalDate, LocalDateTime departureDate, Integer nights) {
        super(user, product);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.nights = nights;
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

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    @Override
    public void setFinalPrice(Double finalPrice) {
        super.setFinalPrice(finalPrice * quantity * nights);
    }

    @Override
    public String toString() {
        return super.toString() +
                '\n' + "Arrival Date: 2021-12-18" +
                '\n' + "Departure Date: 2021-12-20"  +
                '\n' + "Total Nights: " + nights +
                '\n' + "Pension: ALL INCLUSIVE";

    }
}
