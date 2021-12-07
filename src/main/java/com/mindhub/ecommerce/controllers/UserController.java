package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.EventDTO;
import com.mindhub.ecommerce.dtos.HotelDTO;
import com.mindhub.ecommerce.dtos.TicketDTO;
import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.SalesRepository;
import com.mindhub.ecommerce.repositories.UserRepository;
import com.mindhub.ecommerce.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SalesRepository salesRepo;

    @GetMapping("/agencies")
    public Set<UserDTO> getAgencies() {
        return userService.getAgencies();
    }

    @GetMapping("/clients")
    public Set<UserDTO> getClients() {
        return userService.getClients();
    }

    @PostMapping("/agencies/new")
    public ResponseEntity<String> createAgency(@RequestParam String email, @RequestParam String password, @RequestParam String imgUrl, @RequestParam String address, @RequestParam String fantasyName) {
        if (email.isBlank() || password.isBlank() || imgUrl.isBlank() || address.isBlank()) {
            return new ResponseEntity<>("No parameter can be blank", HttpStatus.FORBIDDEN);
        }

        if (userService.createAgency(fantasyName, email, password, imgUrl, address)) {
            return new ResponseEntity<String>("Agency created succesfully", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("Something went wrong, please contact our help desk", HttpStatus.CONFLICT);

    }

    @PostMapping("/clients/new")
    public ResponseEntity<String> createClient(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
        //TODO Chequear que el mail no exista en la base de datos
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("No parameter can be blank", HttpStatus.FORBIDDEN);
        }

        if (userService.createUser(firstName, lastName, email, password)) {
            return new ResponseEntity<String>("Client created succesfully", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("Something went wrong, please contact our help desk", HttpStatus.CONFLICT);


    }

    @GetMapping("/clients/{id}")
    public UserDTO getClient(@PathVariable Long id) {
        return userService.getClientById(id);
    }

    @PostMapping("/clients/addToCart/event")
    public ResponseEntity<String> addEventToCart(Authentication auth, @RequestParam Long eventId, @RequestParam Boolean isVip, @RequestParam Integer attendants) {
        User user = userRepo.findByEmail(auth.getName()).orElse(null);
        Product product = productRepo.findById(eventId).orElse(null);
        if (!(product instanceof Event)) {
            return new ResponseEntity<>("Invalid ID for EVENT", HttpStatus.BAD_REQUEST);
        }

        Event event = (Event) product;
        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);

        }


        userService.addEventToClientCart(user, event, isVip, attendants);

        //TODO
        return new ResponseEntity<String>("Event booked succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/clients/current/addToCart/hotel")
    public ResponseEntity<String> addHotelToCart(Authentication auth, @RequestParam Long hotelId, @RequestParam String arrivalDate, @RequestParam String departureDate, @RequestParam Integer nights, @RequestParam Integer passangers, @RequestParam String pension) {

        User user = userRepo.findByEmail(auth.getName()).orElse(null);
        Product product = productRepo.findById(hotelId).orElse(null);
        if (!(product instanceof Hotel)) {
            return new ResponseEntity<>("Invalid ID for EVENT", HttpStatus.BAD_REQUEST);
        }

        Hotel hotel = (Hotel) product;
        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);

        }
        if (hotel == null) {
            return new ResponseEntity<String>("Event not found", HttpStatus.NOT_FOUND);

        }
        //TODO
        if (userService.addHotelToClientCart(user, hotel, arrivalDate, departureDate, nights, passangers, pension)) {
            return new ResponseEntity<String>("Hotel booked succesfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Booking unsuccessful", HttpStatus.NOT_ACCEPTABLE);


    }

    @PostMapping("/clients/current/addToCart/ticket")
    public ResponseEntity<String> addTicketToCart(Authentication auth, @RequestParam Long ticketId, @RequestParam String clase, @RequestParam Integer passengers) {

        User user = userRepo.findByEmail(auth.getName()).orElse(null);
        Product product = productRepo.findById(ticketId).orElse(null);

        if (!(product instanceof Ticket)) {
            return new ResponseEntity<>("Invalid ID for EVENT", HttpStatus.BAD_REQUEST);
        }

        Ticket ticket = (Ticket) product;

        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        if (ticket == null) {
            return new ResponseEntity<String>("Ticket not found", HttpStatus.NOT_FOUND);
        }


        if (userService.addTicketToClientCart(user, ticket, clase, passengers)) {
            return new ResponseEntity<String>("Ticket booked succesfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Ticket Booking unsuccesful", HttpStatus.CREATED);

        //TODO

    }

    @PostMapping("/clients/current/removeFromCart/ticket")
    public ResponseEntity<String> removeTicketfromCart(Authentication auth, @RequestParam Long userProductId) {

        User user = userRepo.findByEmail(auth.getName()).orElse(null);
        UserProduct toDelete = salesRepo.findById(userProductId).orElse(null);

        if (!(toDelete instanceof ClientTicket)) {
            return new ResponseEntity<>("Invalid ID for Ticket", HttpStatus.BAD_REQUEST);
        }

        ClientTicket ticketToDelete = (ClientTicket) toDelete;

        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }



        if (userService.removeProductFromCart(user, toDelete)) {
            return new ResponseEntity<String>("Ticket booked succesfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Ticket Booking unsuccesful", HttpStatus.CREATED);

        //TODO

    }



}
