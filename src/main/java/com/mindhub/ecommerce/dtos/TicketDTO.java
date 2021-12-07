package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Clase;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;

import java.time.LocalDateTime;

public class TicketDTO extends ProductDTO{

    private String departureTime;
    private String arrivalTime;
    private String departureLocation;
    private String arrivalLocation;


    public TicketDTO() {}

    public TicketDTO(Product product) {
        super(product);
        Ticket ticket = (Ticket) product;
        this.departureLocation=ticket.getDepartureLocation();
        this.arrivalLocation=ticket.getArrivalLocation();
        this.departureTime = ticket.getDepartureTime().toString();
        this.arrivalTime = ticket.getArrivalTime().toString();
    }

    public String getDepartureTime() {return departureTime;}
    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}

    public String getArrivalTime() {return arrivalTime;}
    public void setArrivalTime(String arrivalTime) {this.arrivalTime = arrivalTime;}

    public String getDepartureLocation() {return departureLocation;}
    public void setDepartureLocation(String departureLocation) {this.departureLocation = departureLocation;}

    public String getArrivalLocation() {return arrivalLocation;}
    public void setArrivalLocation(String arrivalLocation) {this.arrivalLocation = arrivalLocation;}



}
