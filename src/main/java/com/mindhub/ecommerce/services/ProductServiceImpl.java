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
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    SalesRepository salesRepos;

    @Override
    public boolean createEvent(EventDTO eventDTO, String agencyName) {
        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        try {
        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Event event = new Event(eventDTO);

        UserProduct offeredEvent = new UserProduct();
        offeredEvent.setProduct(event);
        offeredEvent.setUser(agency);
        productRepo.save(event);
        userRepo.save(agency);
        salesRepos.save(offeredEvent);


        return true;


        } catch (Exception e) {
            return false;

        }

    }

    @Override
    public boolean createTicket(TicketDTO ticketDTO, String agencyName) {
        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        try {
            User agency = userRepo.findByFirstName(agencyName).orElse(null);
            Ticket ticket = new Ticket(ticketDTO);

            UserProduct offeredEvent = new UserProduct();
            offeredEvent.setProduct(ticket);
            offeredEvent.setUser(agency);
            ticket.setUser(agency);
            productRepo.save(ticket);
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    @Override
    public boolean createHotel(HotelDTO hotelDTO, String agencyName) {

        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        try {
            User agency = userRepo.findByFirstName(agencyName).orElse(null);
            Hotel hotel = new Hotel(hotelDTO);
            UserProduct offeredEvent = new UserProduct();
            offeredEvent.setProduct(hotel);
            offeredEvent.setUser(agency);

            hotel.setUser(agency);
            productRepo.save(hotel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
