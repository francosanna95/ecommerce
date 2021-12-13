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
        if(pension ==null) {
            super.setFinalPrice(finalPrice * quantity * nights); // si es vip tiene un recargo del 20%
        }
        else{
            switch(pension){
                case JUST_DINNER:{super.setFinalPrice(finalPrice * quantity * nights + 800);
                                  break;}
                case BREAKFAST_BUFFET:{super.setFinalPrice(finalPrice * quantity * nights+ 1000);
                                        break;}
                case ALL_INCLUSIVE:{super.setFinalPrice(finalPrice * quantity * nights + 1500);
                                    break;}
            }
        }
    }


}
