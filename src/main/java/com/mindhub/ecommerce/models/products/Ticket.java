package com.mindhub.ecommerce.models.products;

import com.mindhub.ecommerce.enums.Clase;
import com.mindhub.ecommerce.models.users.Agency;

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
    private Clase clase; // contador para poner limite a cantidad d pasajeros por clase
    private String asiento;


    public Ticket() {
    }

    public Ticket(Integer points, Double price, String disscountCode, String address, Agency agency, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureLocation, String arrivalLocation, String airport, Clase clase, String asiento,String name) {
        super(points, price, disscountCode, address, agency, name);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.airport = airport;
        this.clase = clase;
        this.asiento = asiento;
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
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
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
