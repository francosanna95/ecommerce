package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByFirstName(String agencyName);
}
