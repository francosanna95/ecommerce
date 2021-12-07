package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.enums.TicketClass;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.SalesRepository;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SalesRepository salesRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName,lastName,email,passwordEncoder.encode(password), UserRole.CLIENT);
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean createAgency(String fantasyName, String  email,String password, String imgUrl, String address) {
        User user = new User(fantasyName,fantasyName,email,passwordEncoder.encode(password), UserRole.AGENCY);
        user.setImgUrl(imgUrl);
        user.setAddress(address);
        userRepo.save(user);
        return true;    }

    @Override
    public Set<UserDTO> getClients() {
        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.CLIENT)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> getAgencies() {
        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.AGENCY)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @Override
    public UserDTO getClientById(Long id) {
        return userRepo.findById(id).map(UserDTO::new).orElse(null);    }

    @Override
    public boolean addEventToClientCart(User user, Event event, Boolean isVip, Integer attendants) {

        try {
            ClientEvent clientEvent = new ClientEvent();
            clientEvent.setUser(user);
            clientEvent.setQuantity(attendants);
            clientEvent.setVip(isVip);
            clientEvent.setProduct(event);
            clientEvent.setFinalPrice(event.getPrice());
            int eventStock = event.getStock();
            event.setStock(eventStock-attendants);
            salesRepo.save(clientEvent);
            userRepo.save(user);
            productRepo.save(event);

            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean addTicketToClientCart(User user, Ticket ticket, String clase, Integer passengers) {

        try {

            ClientTicket clientTicket = new ClientTicket();
            clientTicket.setClase(TicketClass.valueOf(clase.toUpperCase(Locale.ROOT)));
            clientTicket.setUser(user);
            clientTicket.setQuantity(passengers);
            clientTicket.setProduct(ticket);
            clientTicket.setFinalPrice(ticket.getPrice());
            salesRepo.save(clientTicket);
            userRepo.save(user);
            productRepo.save(ticket);

            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addHotelToClientCart(User user, Hotel hotel,String arrivalDate, String departureDate, Integer nights, Integer passangers, String pension ) {
        LocalDateTime arrival = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ISO_DATE_TIME) ;
        LocalDateTime departure = LocalDateTime.parse(departureDate, DateTimeFormatter.ISO_DATE_TIME) ;
        Pension pensionChoice = Pension.valueOf(pension);
        try {

            ClientHotel clientHotel = new ClientHotel();
            clientHotel.setPension(pensionChoice);
            clientHotel.setUser(user);
            clientHotel.setQuantity(passangers);
            clientHotel.setProduct(hotel);
            clientHotel.setFinalPrice(hotel.getPrice());
            salesRepo.save(clientHotel);
            userRepo.save(user);
            productRepo.save(hotel);

            return true;
        } catch (Exception e){
            return false;
        }
    }



}
