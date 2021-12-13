package com.mindhub.ecommerce.configuration;

import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> {
            com.mindhub.ecommerce.models.User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                if (user.getUserRole().toString().equals("AGENCY")) {
                    return new User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
                }
                return new User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("CLIENT"));
            } else {
                throw new UsernameNotFoundException("Unknown user" + email);
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
