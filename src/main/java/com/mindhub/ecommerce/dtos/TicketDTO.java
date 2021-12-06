package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Clase;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;

import java.time.LocalDateTime;

public class TicketDTO extends ProductDTO{

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private String airport;
    private Clase clase;
    private String seat;

    public TicketDTO() {}
    public TicketDTO(Product product) {
        super(product);
        Ticket ticket = (Ticket) product;
        this.departureLocation=ticket.getDepartureLocation();
        this.arrivalLocation=ticket.getArrivalLocation();
        this.airport=ticket.getAirport();
        this.clase=ticket.getClase();
    }

    public LocalDateTime getDepartureTime() {return departureTime;}
    public void setDepartureTime(LocalDateTime departureTime) {this.departureTime = departureTime;}

    public LocalDateTime getArrivalTime() {return arrivalTime;}
    public void setArrivalTime(LocalDateTime arrivalTime) {this.arrivalTime = arrivalTime;}

    public String getDepartureLocation() {return departureLocation;}
    public void setDepartureLocation(String departureLocation) {this.departureLocation = departureLocation;}

    public String getArrivalLocation() {return arrivalLocation;}
    public void setArrivalLocation(String arrivalLocation) {this.arrivalLocation = arrivalLocation;}

    public String getAirport() {return airport;}
    public void setAirport(String airport) {this.airport = airport;}

    public Clase getClase() {return clase;}
    public void setClase(Clase clase) {this.clase = clase;}

    public String getSeat() {return seat;}
    public void setSeat(String seat) {this.seat = seat;}
}
