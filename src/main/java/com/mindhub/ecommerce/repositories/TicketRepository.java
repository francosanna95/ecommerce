package com.mindhub.ecommerce.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource
public interface TicketRepository extends org.springframework.data.jpa.repository.JpaRepository<com.mindhub.ecommerce.models.products.Product, Long> {
}
