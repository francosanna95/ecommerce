package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.products.Product;
import com.mindhub.ecommerce.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@NoRepositoryBean
public interface ProductRepository<T extends Product, L extends Number> extends JpaRepository<Product, Long> {
}
