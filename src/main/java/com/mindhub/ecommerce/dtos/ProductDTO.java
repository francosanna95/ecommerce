package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.products.Event;
import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.models.products.Product;
import com.mindhub.ecommerce.models.products.Ticket;

public class ProductDTO {
    private Long productId;
    private Integer points;
    private Double price;
    private String disscountCode;
    private String address;
    private UserDTO agency;
    private EventDTO eventDTO;
    private HotelDTO hotelDTO;
    private TicketDTO ticketDTO;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.points = product.getPoints();
        this.price = product.getPrice();
        this.disscountCode = product.getDisscountCode();
        this.address = product.getAddress();
        this.agency = new UserDTO(product.getAgency());

        if (product instanceof Event){
            this.eventDTO = new EventDTO((Event) product);
        } else if( product instanceof Hotel) {
            this.hotelDTO  = new HotelDTO((Hotel) product);
        } else if (product instanceof Ticket){
            this.ticketDTO = new TicketDTO((Ticket) product);
        }
    }
}
