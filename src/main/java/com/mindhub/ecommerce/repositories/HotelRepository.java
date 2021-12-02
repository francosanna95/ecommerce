package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.models.products.Product;
import com.mindhub.ecommerce.models.users.Client;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource
public interface HotelRepository extends ProductRepository<Hotel,Long>{
}