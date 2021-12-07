package com.mindhub.ecommerce.services;


import com.mindhub.ecommerce.dtos.UserDTO;

import java.util.Set;

public interface UserService {
    boolean createUser(String firstName, String lastName, String email, String password);
    boolean createAgency(String fantasyName, String  email,String password, String imgUrl, String address);
    Set<UserDTO> getClients();
    Set<UserDTO> getAgencies();
    UserDTO getClientById(Long id);
}
