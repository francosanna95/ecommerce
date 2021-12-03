package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.models.users.ClientProducts;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class HotelDTO {
    private Long productId;
    private Integer points;
    private Double price;
    private String disscountCode;
    private String address;
    private Integer nights;
    private Integer passengers;
    private String room;
    private Pension pension;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private AgencyDTO agency;
    private Set<ClientProducts> clientProducts = new HashSet();

    public HotelDTO() {}
    public HotelDTO(Hotel hotel) {
        this.productId=hotel.getProductId();
        this.points=hotel.getPoints();
        this.price=hotel.getPrice();
        this.disscountCode=hotel.getDisscountCode();
        this.address=hotel.getAddress();
  //      this.agency=hotel.getAgency();
        this.clientProducts=hotel.getClientProducts();
        this.nights=hotel.getNights();
        this.passengers=hotel.getPassengers();
        this.room=hotel.getRoom();
        this.pension=hotel.getPension();
        this.arrivalDate=hotel.getArrivalDate();
        this.departureDate=hotel.getDepartureDate();
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

    public Integer getNights() {return nights;}
    public void setNights(Integer nights) {this.nights = nights;}

    public Integer getPassengers() {return passengers;}
    public void setPassengers(Integer passengers) {this.passengers = passengers;}

    public String getRoom() {return room;}
    public void setRoom(String room) {this.room = room;}

    public Pension getPension() {return pension;}
    public void setPension(Pension pension) {this.pension = pension;}

    public LocalDateTime getArrivalDate() {return arrivalDate;}
    public void setArrivalDate(LocalDateTime arrivalDate) {this.arrivalDate = arrivalDate;}

    public LocalDateTime getDepartureDate() {return departureDate;}
    public void setDepartureDate(LocalDateTime departureDate) {this.departureDate = departureDate;}

    public AgencyDTO getAgency() {return agency;}
    public void setAgency(AgencyDTO agency){this.agency=agency;}

    public Set<ClientProducts> getClientProducts() {return clientProducts;}
    public void setClientProducts(Set<ClientProducts> clientProducts) {this.clientProducts = clientProducts;}
}
