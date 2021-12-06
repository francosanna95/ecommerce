package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.dtos.TicketDTO;
import com.mindhub.ecommerce.enums.Clase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends Product{

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private String airport;
    private Clase clases;

    public Ticket() {
        super();

    }

    public Ticket(Integer points, Double price, String disscountCode, String address, User user, String name, Integer maxCapacity, Integer stock, String imgUrl, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureLocation, String arrivalLocation, String airport, Clase clases) {
        super(points, price, disscountCode, address, user, name, maxCapacity, stock, imgUrl);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.airport = airport;
        this.clases = clases;
    }


    //Constructor para generar un Producto a partir del Controlador con el RequestBody viniendo desde el Front y recibiendolo como DTO
    public Ticket(TicketDTO ticketDTO) {
        super(ticketDTO.getPoints(), ticketDTO.getPrice(), ticketDTO.getDisscountCode(), ticketDTO.getAddress(), ticketDTO.getProductName(), ticketDTO.getMaxCapacity(), ticketDTO.getStock(), ticketDTO.getImgUrl());
        this.departureTime = ticketDTO.getDepartureTime();
        this.arrivalTime = ticketDTO.getArrivalTime();
        this.departureLocation = ticketDTO.getDepartureLocation();
        this.arrivalLocation = ticketDTO.getArrivalLocation();
        this.airport = ticketDTO.getAirport();
        this.clases = ticketDTO.getClase();
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public Clase getClase() {
        return clases;
    }
    public void setClase(Clase clases) {
        this.clases = clases;
    }



    @Override
    public String toString() {
        return "Ticket{" +
                "departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", airport='" + airport + '\'' +
                '}';
    }
}
