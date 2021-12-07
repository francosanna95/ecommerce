package com.mindhub.ecommerce.services;


import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.models.*;

import java.util.Set;

public interface UserService {
    boolean createUser(String firstName, String lastName, String email, String password);

    boolean createAgency(String fantasyName, String email, String password, String imgUrl, String address);

    Set<UserDTO> getClients();

    Set<UserDTO> getAgencies();

    UserDTO getClientById(Long id);

    boolean addEventToClientCart(User user, Event event, Boolean isVip, Integer attendants);

    boolean addTicketToClientCart(User user, Ticket ticket, String clase, Integer passengers);

    boolean addHotelToClientCart(User user, Hotel hotel, String arrivalDate, String departureDate, Integer nights, Integer passangers, String pension);

    boolean removeProductFromCart(User user, UserProduct toDelete);
}
