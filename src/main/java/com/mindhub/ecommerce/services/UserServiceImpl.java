package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.users.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String lastName, String email, String password) {
        //TODO: Service for new client registration
        return null;
    }
}
