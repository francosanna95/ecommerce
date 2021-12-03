package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.repositories.UserRepository;
import com.mindhub.ecommerce.services.AgencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AgencyServiceImpl agencyService;

    @GetMapping("/agencies")
    public Set<UserDTO> getAgencies() {
        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.AGENCY)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/clients")
    public Set<UserDTO> getClients() {
        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.CLIENT)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @PostMapping("/agencies/new")
    public ResponseEntity<String> createAgency(@RequestParam String email, @RequestParam String password, @RequestParam String imgUrl, @RequestParam String address) {


        return new ResponseEntity<String>("Client created succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/clients/new")
    public ResponseEntity<String> createClient(@RequestParam String fantasyName, @RequestParam String email, @RequestParam String password, @RequestParam String address) {

        return new ResponseEntity<String>("Client created succesfully", HttpStatus.CREATED);
    }

}
