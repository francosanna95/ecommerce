package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.products.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}