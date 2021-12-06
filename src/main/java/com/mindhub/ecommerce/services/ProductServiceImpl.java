package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.dtos.EventDTO;
import com.mindhub.ecommerce.dtos.HotelDTO;
import com.mindhub.ecommerce.dtos.TicketDTO;
import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.SalesRepository;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    SalesRepository salesRepos;

    @Override
    public boolean createEvent(EventDTO eventDTO, String agencyName) {
        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Event event = new Event(eventDTO);

        UserProduct offeredEvent = new UserProduct();
        offeredEvent.setProduct(event);
        offeredEvent.setUser(agency);

        salesRepos.save(offeredEvent);
        userRepo.save(agency);
        productRepo.save(event);

        if (productRepo.existsById(event.getProductId())) {
            return true;
        }


        return false;
    }

    @Override
    public boolean createTicket(TicketDTO ticketDTO, String agencyName) {
        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto

        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Ticket ticket = new Ticket(ticketDTO);
        ticket.setUser(agency);

        productRepo.save(ticket);
        if (productRepo.existsById(ticketDTO.getProductId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean createHotel(HotelDTO hotelDTO, String agencyName) {

        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Hotel hotel = new Hotel(hotelDTO);
        hotel.setUser(agency);

        productRepo.save(hotel);
        if (productRepo.existsById(hotelDTO.getProductId())) {
            return true;
        }
        return false;
    }
}
