package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.users.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional
@RepositoryRestResource
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Optional<Agency> findByFantasyName(Long aLong);
}
