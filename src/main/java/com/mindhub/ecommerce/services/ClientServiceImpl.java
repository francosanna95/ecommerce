package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.users.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
    @Override
    public Client createClient(String firstName, String lastName, String email, String password) {
        //TODO: Service for new client registration
        return null;
    }
}
