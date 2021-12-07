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
    public boolean createEvent(EventDTO eventDTO, User agency) {
        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        try {
            Event event = new Event(eventDTO);
    //Si el producto ya existe no lo creo
            if (productRepo.findByName(event.getName()).isPresent()) {
                return false;
            }
            event.setUser(agency);
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
    public boolean createTicket(TicketDTO ticketDTO, User agency) {
        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        try {
            Ticket ticket = new Ticket(ticketDTO);

            if (productRepo.findByName(ticket.getName()).isPresent()) {
                return false;
            }
            ticket.setUser(agency);
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
    public boolean createHotel(HotelDTO hotelDTO, User agency) {

        //TODO cambiar agencyName por Authentication auth, y de ahi obtener el email de la agencia q esta queriendo crear el producto
        try {

            Hotel hotel = new Hotel(hotelDTO);

            if (productRepo.findByName(hotel.getName()).isPresent()) {
                return false;
            }
            hotel.setUser(agency);

            UserProduct offeredEvent = new UserProduct();
            offeredEvent.setProduct(hotel);
            offeredEvent.setUser(agency);

            productRepo.save(hotel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
