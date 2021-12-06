package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.*;
import com.mindhub.ecommerce.models.User;
import com.mindhub.ecommerce.models.Event;
import com.mindhub.ecommerce.models.Hotel;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.repositories.UserRepository;
import com.mindhub.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;


    @GetMapping("/events")
    public List<ProductDTO> getEvents() {
        return productRepo.findAll().stream().filter(product -> product instanceof Event).map(EventDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/hotels")
    public List<ProductDTO> getHotels() {
        return productRepo.findAll().stream().filter(product -> product instanceof Hotel).map(HotelDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/tickets")
    public List<ProductDTO> getTickets() {
        return productRepo.findAll().stream().filter(product -> product instanceof Ticket).map(TicketDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream().map(ProductDTO::new).sorted((o1, o2) -> o1.getProductType().compareTo(o2.getProductType())).collect(Collectors.toList());
    }

    @PostMapping("/events")
    public ResponseEntity<String> addEvent(@RequestParam String artist, @RequestParam Boolean isVip, @RequestParam Integer points, @RequestParam Integer stock, @RequestParam String imgUrl, @RequestParam Integer maxCapacity, @RequestParam String agencyName, @RequestParam Double price, @RequestParam String disscountCode, @RequestParam String eventName, @RequestParam String address) {

        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Event event = new Event(points,price,disscountCode,address,eventName,maxCapacity,stock,imgUrl,artist,isVip);
        productRepo.save(event);
        return new ResponseEntity<>("Todo bien", HttpStatus.ACCEPTED);
    }

    @PostMapping("/ticket")
    public ResponseEntity<String> addTicket(@RequestBody TicketDTO ticketDTO, @RequestParam String agencyName) {
        User agency = userRepo.findByFirstName(agencyName).orElse(null);

        Ticket ticket = new Ticket(ticketDTO);
        ticket.setUser(agency);

        productRepo.save(ticket);
        return new ResponseEntity<>("Todo bien", HttpStatus.ACCEPTED);
    }
    @PostMapping("/hotels")
     public ResponseEntity<String> addHotel(@RequestBody HotelDTO hotelDTO, @RequestParam String agencyName) {
         User agency = userRepo.findByFirstName(agencyName).orElse(null);
         Hotel hotel=new Hotel(hotelDTO);
         hotel.setUser(agency);
         productRepo.save(hotel);
         return new ResponseEntity<>("Hotel agregado",HttpStatus.ACCEPTED);
     }
}
