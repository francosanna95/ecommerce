package com.mindhub.ecommerce;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.users.Agency;
import com.mindhub.ecommerce.models.users.Client;
import com.mindhub.ecommerce.repositories.AgencyRepository;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(UserRepository clientRepo,
									  AgencyRepository agencyRepo,
									  UserRepository userRepo) {
		return (args) -> {

			Client client = new Client();
			client.setFirstName("Melba");
			client.setLastName("Morel");
			client.setEmail("melba@mindhub.com");
			client.setPassword("hola123");
			client.setUserRol(UserRole.CLIENT);

			clientRepo.save(client);
			userRepo.save(client);

			Agency agency = new Agency();
			agency.setFirstName("Travel");
			agency.setLastName("Agency");
			agency.setAddress("Avenida Siempre Viva 123");
			agency.setEmail("travel@agency.com");
			agency.setPassword("agency123");
			agency.setUserRol(UserRole.AGENCY);

			agencyRepo.save(agency);
			userRepo.save(agency);

		};
	}
}
