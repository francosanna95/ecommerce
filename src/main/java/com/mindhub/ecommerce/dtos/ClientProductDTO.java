package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.products.Event;
import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.models.products.Product;
import com.mindhub.ecommerce.models.products.Ticket;
import com.mindhub.ecommerce.models.users.Client;
import com.mindhub.ecommerce.models.users.ClientProducts;

public class ClientProductDTO {
    private long id;
    private String productName;
    private double productPrice;

    public ClientProductDTO() {}
    public ClientProductDTO(ClientProducts clientProducts) {
//        Product product = clientProducts.getProduct();
//        if (product instanceof Event){
//            Event evento = (Event) product;//
//        } else if( product instanceof Hotel) {
//            Hotel hotel = (Hotel) product;
//
//        } else if (product instanceof Ticket)

        this.id=clientProducts.getId();
        this.productName=clientProducts.getProduct().getName();
        this.productPrice=clientProducts.getProduct().getPrice();
    }
}
