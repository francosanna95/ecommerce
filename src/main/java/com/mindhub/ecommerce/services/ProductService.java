package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.dtos.EventDTO;
import com.mindhub.ecommerce.dtos.HotelDTO;
import com.mindhub.ecommerce.dtos.TicketDTO;
import com.mindhub.ecommerce.models.User;

public interface ProductService {
    boolean createEvent(EventDTO eventDTO, User agency);

    boolean createTicket(TicketDTO ticketDTO,User agency);

    boolean createHotel(HotelDTO hotelDTO, User agency);
}
