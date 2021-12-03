package com.mindhub.ecommerce.models.users;

import com.mindhub.ecommerce.models.products.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private  Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    public ClientProducts() {}
    public ClientProducts(Client client, Product product) {
        this.client=client;
        this.product=product;
    }

    public long getId() {return id;}

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
}
