package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.users.Agency;
import com.mindhub.ecommerce.models.users.Client;
import com.mindhub.ecommerce.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

}
