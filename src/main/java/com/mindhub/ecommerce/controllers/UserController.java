package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.repositories.UserRepository;
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


    @GetMapping("/agencies")
    public Set<UserDTO> getAgencies() {
        //TODO Agregar CAPA de Servicio

        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.AGENCY)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/clients")
    public Set<UserDTO> getClients() {
        //TODO Agregar CAPA de Servicio



        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.CLIENT)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @PostMapping("/agencies/new")
    public ResponseEntity<String> createAgency(@RequestParam String email, @RequestParam String password, @RequestParam String imgUrl, @RequestParam String address) {
        //TODO Agregar CAPA de Servicio


        return new ResponseEntity<String>("Client created succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/clients/new")
    public ResponseEntity<String> createClient(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {

        //TODO Agregar CAPA de Servicio





        return new ResponseEntity<String>("Client created succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}")
    public UserDTO getClient(@PathVariable Long id) {
        //TODO Agregar CAPA de Servicio

        return userRepo.findById(id).map(UserDTO::new).orElse(null);
    }


}
