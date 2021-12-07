package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.dtos.TicketDTO;
import com.mindhub.ecommerce.enums.Clase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tickets")
public class Ticket extends Product {

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureLocation;
    private String arrivalLocation;

    public Ticket() {

    }

    public Ticket(Integer points, Double price, String disscountCode, String address, User user, String name, String description, Integer stock, String imgUrl, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureLocation, String arrivalLocation) {
        super(points, price, disscountCode, address, user, name, description, stock, imgUrl);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }


    //Constructor para generar un Producto a partir del Controlador con el RequestBody viniendo desde el Front y recibiendolo como DTO
    public Ticket(TicketDTO ticketDTO) {
        super(ticketDTO.getPoints(), ticketDTO.getPrice(), ticketDTO.getDisscountCode(), ticketDTO.getAddress(), ticketDTO.getProductName(), ticketDTO.getDescription(), ticketDTO.getStock(), ticketDTO.getImgUrl());
        this.departureTime = LocalDateTime.parse(ticketDTO.getDepartureTime(), DateTimeFormatter.ISO_DATE_TIME);
        this.arrivalTime = LocalDateTime.parse(ticketDTO.getArrivalTime(), DateTimeFormatter.ISO_DATE_TIME);
        this.departureLocation = ticketDTO.getDepartureLocation();
        this.arrivalLocation = ticketDTO.getArrivalLocation();
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


    @Override
    public String toString() {
        return "Ticket{" +
                "departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                '}';
    }
}
