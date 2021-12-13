package com.mindhub.ecommerce.models;

import com.mindhub.ecommerce.dtos.TicketDTO;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tickets")
public class Ticket extends Product {

    private String departureTime;
    private String arrivalTime;
    private String departureLocation;
    private String arrivalLocation;

    public Ticket() {

    }

    public Ticket(Integer points, Double price, String disscountCode, String address, User user, String name, String description, Integer stock, String imgUrl, String departureTime, String arrivalTime, String departureLocation, String arrivalLocation) {
        super(points, price, disscountCode, address, user, name, description, stock, imgUrl);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }


    //Constructor para generar un Producto a partir del Controlador con el RequestBody viniendo desde el Front y recibiendolo como DTO
    public Ticket(TicketDTO ticketDTO) {
        super(ticketDTO.getPoints(), ticketDTO.getPrice(), ticketDTO.getDisscountCode(), ticketDTO.getAddress(), ticketDTO.getProductName(), ticketDTO.getDescription(), ticketDTO.getStock(), ticketDTO.getImgUrl());
        this.departureTime = ticketDTO.getDepartureTime();
        this.arrivalTime = ticketDTO.getArrivalTime();
        this.departureLocation = ticketDTO.getDepartureLocation();
        this.arrivalLocation = ticketDTO.getArrivalLocation();
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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
