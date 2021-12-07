package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SalesRepository extends JpaRepository<UserProduct,Long> {





}
