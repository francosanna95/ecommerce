package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.users.Client;
import org.springframework.stereotype.Service;

public interface ClientService {
    Client createClient(String firstName, String lastName, String email, String password);
}
