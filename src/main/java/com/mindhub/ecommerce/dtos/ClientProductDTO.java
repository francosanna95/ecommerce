package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.products.Product;
import com.mindhub.ecommerce.models.users.Client;
import com.mindhub.ecommerce.models.users.ClientProducts;

public class ClientProductDTO {
    private long id;
    private String productName;
    private double productPrice;

    public ClientProductDTO() {}
    public ClientProductDTO(ClientProducts clientProducts) {
        this.id=clientProducts.getId();
        this.productName=clientProducts.getProduct().getName();
        this.productPrice=clientProducts.getProduct().getPrice();
    }
}
