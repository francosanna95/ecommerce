package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.users.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean

public interface UserRepository<T extends User, Long> extends JpaRepository<User, Long> {

}
