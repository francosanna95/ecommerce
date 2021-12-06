package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.dtos.EventDTO;
import com.mindhub.ecommerce.dtos.HotelDTO;
import com.mindhub.ecommerce.dtos.TicketDTO;
import com.mindhub.ecommerce.models.Event;
import com.mindhub.ecommerce.models.Hotel;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.models.User;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserRepository userRepo;

    @Override
    public boolean createEvent(EventDTO eventDTO, String agencyName) {
        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Event event = new Event(eventDTO);

        productRepo.save(event);
        if (productRepo.existsById(event.getProductId())){
        return true;
        }
        return false;
    }

    @Override
    public boolean createTicket(TicketDTO ticketDTO, String agencyName) {
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
        User agency = userRepo.findByFirstName(agencyName).orElse(null);
        Hotel hotel=new Hotel(hotelDTO);
        hotel.setUser(agency);

        productRepo.save(hotel);
            return true;
    }
}
