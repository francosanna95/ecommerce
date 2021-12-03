package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Clase;
import com.mindhub.ecommerce.models.products.Ticket;
import com.mindhub.ecommerce.models.users.ClientProducts;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TicketDTO {
    private Long productId;
    private Integer points;
    private Double price;
    private String disscountCode;
    private String address;
    private AgencyDTO agency;
    private Set<ClientProducts> clientProducts = new HashSet();
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private String airport;
    private Clase clase; // contador para poner limite a cantidad d pasajeros por clase
    private String seat;

    public TicketDTO() {}
    public TicketDTO(Ticket ticket) {
        this.productId=ticket.getProductId();
        this.points=ticket.getPoints();
        this.price=ticket.getPrice();
        this.disscountCode=ticket.getDisscountCode();
        this.address=ticket.getAddress();
   //     this.agency=ticket.getAgency();
        this.clientProducts=ticket.getClientProducts();
        this.departureLocation=ticket.getDepartureLocation();
        this.arrivalLocation=ticket.getArrivalLocation();
        this.airport=ticket.getAirport();
        this.clase=ticket.getClase();
        this.seat=ticket.getAsiento();
    }

    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}

    public Integer getPoints() {return points;}
    public void setPoints(Integer points) {this.points = points;}

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}

    public String getDisscountCode() {return disscountCode;}
    public void setDisscountCode(String disscountCode) {this.disscountCode = disscountCode;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public AgencyDTO getAgency() {return agency;}
    public void setAgency(AgencyDTO agency) {this.agency = agency;}

    public Set<ClientProducts> getClientProducts() {return clientProducts;}
    public void setClientProducts(Set<ClientProducts> clientProducts) {this.clientProducts = clientProducts;}

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
