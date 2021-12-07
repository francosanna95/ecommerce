package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.User;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName,lastName,email,passwordEncoder.encode(password), UserRole.CLIENT);
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean createAgency(String fantasyName, String  email,String password, String imgUrl, String address) {
        User user = new User(fantasyName,fantasyName,email,passwordEncoder.encode(password), UserRole.AGENCY);
        user.setImgUrl(imgUrl);
        user.setAddress(address);
        userRepo.save(user);
        return true;    }

    @Override
    public Set<UserDTO> getClients() {
        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.CLIENT)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> getAgencies() {
        return userRepo.findAll().stream().filter(user -> user.getUserRole().equals(UserRole.AGENCY)).map(UserDTO::new).collect(Collectors.toSet());
    }

    @Override
    public UserDTO getClientById(Long id) {
        return userRepo.findById(id).map(UserDTO::new).orElse(null);    }


}
