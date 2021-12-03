package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.users.Agency;
import org.springframework.web.bind.annotation.RequestParam;

public interface AgencyService {
    Agency createAgency(String fantasyName, String email, String password, String address);
}
