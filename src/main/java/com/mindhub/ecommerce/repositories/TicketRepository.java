package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.models.products.Ticket;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource
public interface TicketRepository extends ProductRepository<Ticket,Long>{
}
