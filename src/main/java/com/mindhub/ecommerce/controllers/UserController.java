package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.repositories.UserRepository;
import com.mindhub.ecommerce.services.UserServiceImpl;
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
    private UserServiceImpl userService;


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


}
