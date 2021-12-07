package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.*;
import com.mindhub.ecommerce.models.User;
import com.mindhub.ecommerce.models.Event;
import com.mindhub.ecommerce.models.Hotel;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.repositories.UserRepository;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.services.ProductService;
import com.mindhub.ecommerce.services.ProductServiceImpl;
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

    @Autowired
    private ProductServiceImpl productService;


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
    public ResponseEntity<String> addEvent(@RequestBody EventDTO eventDTO) {
        if (eventDTO.getDescription().isBlank()){
            return new ResponseEntity<>("Please set Event Description",HttpStatus.NOT_ACCEPTABLE);
        }
        if (eventDTO.getProductName().isBlank()) {
            return new ResponseEntity<>("The name can't be blank", HttpStatus.NOT_ACCEPTABLE);
        }
        if (eventDTO.getPrice()<0){
            return new ResponseEntity<>("The price should be higher tan 0",HttpStatus.NOT_ACCEPTABLE);
        }
        if (eventDTO.getAddress().isBlank()){
            return new ResponseEntity<>("Address can't be null",HttpStatus.NOT_ACCEPTABLE);
        }

        if (eventDTO.getStock()<0){
            return new ResponseEntity<>("Stock should be higher tan 0",HttpStatus.NOT_ACCEPTABLE);
        }

        if (userRepo.findByFirstName(eventDTO.getAgencyName()).isEmpty()){
            return new ResponseEntity<>("Invalid agency name",HttpStatus.NOT_ACCEPTABLE);
        }
        if (productService.createEvent(eventDTO,eventDTO.getAgencyName())){
            return new ResponseEntity<>("Evento creado", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/tickets")
    public ResponseEntity<String> addTicket(@RequestBody TicketDTO ticketDTO) {
        if (userRepo.findByFirstName(ticketDTO.getAgencyName()).isEmpty()){
            return new ResponseEntity<>("Invalid agency name",HttpStatus.NOT_ACCEPTABLE);
        }

        if (ticketDTO.getProductName().isBlank()) {
            return new ResponseEntity<>("The name can't be blank", HttpStatus.NOT_ACCEPTABLE);
        }
        if (ticketDTO.getPrice()<0){
            return new ResponseEntity<>("The price should be positive numbers",HttpStatus.NOT_ACCEPTABLE);
        }

        if (ticketDTO.getStock()<0){
            return new ResponseEntity<>("You can't have negative stock",HttpStatus.NOT_ACCEPTABLE);
        }

        if (productService.createTicket(ticketDTO,ticketDTO.getAgencyName())) {
            return new ResponseEntity<>("Ticked creado", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Creation ticket error, please contact our Help Desk",HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("/hotels")
     public ResponseEntity<String> addHotel(@RequestBody HotelDTO hotelDTO) {

        if (hotelDTO.getAddress().isBlank()){
            return new ResponseEntity<>("Please set address",HttpStatus.NOT_ACCEPTABLE);
        }
        if (hotelDTO.getPrice()<0){
            return new ResponseEntity<>("The price should be positive numbers",HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepo.findByFirstName(hotelDTO.getAgencyName()).isEmpty()){
            return new ResponseEntity<>("Invalid Agency",HttpStatus.NOT_ACCEPTABLE);
        }
        if (hotelDTO.getProductName().isBlank()){
            return new ResponseEntity<>("The name can't be blank",HttpStatus.NOT_ACCEPTABLE);
        }
        if (productService.createHotel(hotelDTO,hotelDTO.getAgencyName())){
         return new ResponseEntity<>("Hotel creation successful",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Hotel creation error",HttpStatus.NOT_ACCEPTABLE);
     }
}
