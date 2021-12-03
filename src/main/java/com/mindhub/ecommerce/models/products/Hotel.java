package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.models.users.Agency;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotels")
public class Hotel extends Product{

    private Integer nights;
    private Integer passengers;
    private String room;
    private Pension pension;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;



    public Hotel() {
    }

    public Hotel(Integer points, Double price, String discountCode, String address, Agency agency, Integer nights, Integer passengers, String room, Pension pension, LocalDateTime arrivalDate, LocalDateTime departureDate) {
        super(points, price, discountCode, address, agency);
        this.nights = nights;
        this.passengers = passengers;
        this.room = room;
        this.pension = pension;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
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

    @Override
    public void setPrice(Double price) {
        super.setPrice(price * this.nights * this.passengers);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nights=" + nights +
                ", passengers=" + passengers +
                ", room='" + room + '\'' +
                ", pension=" + pension +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                '}';
    }
}
