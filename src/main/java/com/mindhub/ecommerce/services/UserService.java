package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.users.User;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password);
}
