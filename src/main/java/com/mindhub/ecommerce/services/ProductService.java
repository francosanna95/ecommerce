package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.dtos.EventDTO;
import com.mindhub.ecommerce.dtos.HotelDTO;
import com.mindhub.ecommerce.dtos.TicketDTO;

public interface ProductService {
    boolean createEvent(EventDTO eventDTO, String agencyName);

    boolean createTicket(TicketDTO ticketDTO,String agencyName);

    boolean createHotel(HotelDTO hotelDTO,String agencyName);
}
