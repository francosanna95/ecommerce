package com.mindhub.ecommerce.services;


import com.itextpdf.layout.Document;
import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.dtos.UserProductDTO;
import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.models.*;

import java.io.ByteArrayOutputStream;
import java.util.Set;

public interface UserService {
    boolean createUser(String firstName, String lastName, String email, String password,String rol);

    boolean createAgency(String fantasyName, String email, String password, String imgUrl, String address);

    Set<UserDTO> getClients();

    Set<UserDTO> getAgencies();

    UserDTO getClientById(Long id);

    UserProductDTO addEventToClientCart(User user, Event event, Boolean isVip, Integer attendants);

    UserProductDTO addTicketToClientCart(User user, Ticket ticket, String clase, Integer passengers);

    UserProductDTO addHotelToClientCart(User user, Hotel hotel, Integer nights, Integer passangers);

    boolean removeProductFromCart(User user, UserProduct toDelete);

    boolean add1ProductToCart(User user,UserProduct toAdd);

    boolean sendInvoice(User user, byte[] bytes);

    boolean createHistoryCart(User user);

    boolean sendHelpMesagge(String firstName, String lastName, String email, String country, String comment);

    boolean updatePic(User user, String imgUrl);
}
