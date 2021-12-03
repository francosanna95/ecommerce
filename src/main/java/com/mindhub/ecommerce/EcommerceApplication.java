package com.mindhub.ecommerce;

import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.products.Event;
import com.mindhub.ecommerce.models.products.Hotel;
import com.mindhub.ecommerce.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner initData(UserRepository clientRepo,
//                                      AgencyRepository agencyRepo,
//                                      EventRepository eventRepo,
//                                      HotelRepository hotelRepo) {
//        return (args) -> {

//            Client client = new Client();
//            client.setFirstName("Melba");
//            client.setLastName("Morel");
//            client.setEmail("melba@mindhub.com");
//            client.setPassword("hola123");
//            client.setUserRole(UserRole.CLIENT);

//            clientRepo.save(client);
//            Agency agency = new Agency();
//            agency.setFantasyName("Travel Rock");
//            agency.setAddress("Avenida Siempre Viva 123");
//            agency.setEmail("travel@agency.com");
//            agency.setPassword("agency123");
//            agency.setUserRole(UserRole.AGENCY);

//            agencyRepo.save(agency);

//            Event concierto = new Event();
//            Hotel hospedaje = new Hotel();

        //    concierto.setAgency(agency);
//            concierto.setDisscountCode("NONE");
//            concierto.setPrice(2500D);
//            concierto.setPoints(100);
//            concierto.setArtist("MALUMA");
//            concierto.setAddress("MIAMI");

//            hospedaje.setPoints(50);
//            hospedaje.setPassengers(3);
//            hospedaje.setNights(5);
//            hospedaje.setPrice(3500D);
       //     hospedaje.setAgency(agency);
//            hospedaje.setDisscountCode("HOLIDAYS2020");

//            eventRepo.save(concierto);
//            hotelRepo.save(hospedaje);

     //       agency.getAvailableProducts().add(concierto);
     //       agency.getAvailableProducts().add(hospedaje);
     //       agencyRepo.save(agency);
//        };
//    }
}
