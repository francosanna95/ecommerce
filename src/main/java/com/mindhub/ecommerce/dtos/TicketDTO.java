package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Clase;
import com.mindhub.ecommerce.models.products.Ticket;
import com.mindhub.ecommerce.models.ClientProduct;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TicketDTO {

    private Set<ClientProduct> clientProducts = new HashSet();
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private String airport;
    private Clase clase; // contador para poner limite a cantidad d pasajeros por clase
    private String seat;

    public TicketDTO() {}
    public TicketDTO(Ticket ticket) {

        this.clientProducts=ticket.getClientProducts();
        this.departureLocation=ticket.getDepartureLocation();
        this.arrivalLocation=ticket.getArrivalLocation();
        this.airport=ticket.getAirport();
        this.clase=ticket.getClase();
        this.seat=ticket.getAsiento();
    }

    public Set<ClientProduct> getClientProducts() {return clientProducts;}
    public void setClientProducts(Set<ClientProduct> clientProducts) {this.clientProducts = clientProducts;}

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
