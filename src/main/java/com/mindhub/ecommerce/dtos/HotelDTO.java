package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.models.Hotel;
import com.mindhub.ecommerce.models.Product;

public class HotelDTO extends ProductDTO{

    private Boolean parking;
    private Boolean concierge;

    public HotelDTO() {}

    public HotelDTO(Product product) {
        super(product);
        Hotel hotel = (Hotel) product;
        this.parking = hotel.getParking();
        this.concierge = hotel.getConcierge();
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
