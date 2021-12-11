package com.mindhub.ecommerce;

import com.mindhub.ecommerce.enums.TicketClass;
import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.repositories.*;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(UserRepository userRepo,
                                      ProductRepository productRepo,
                                      SalesRepository salesRepos) {
        return (args) -> {

            User admin = new User();
            admin.setUserRole(UserRole.ADMIN);
            admin.setEmail("melbas.trips@gmail.com");
            admin.setPassword(passwordEncoder.encode("teamparis123"));
            userRepo.save(admin);

            //Creación de agencia
            User agencyAndamio = new User("Andiamo Viajes", "Andiamo Viajes", "travel@agency.com", passwordEncoder.encode("12345678"), UserRole.AGENCY);
            agencyAndamio.setImgUrl("https://res.cloudinary.com/diyps0xa6/image/upload/v1638710245/Ecommerce/profile_pics/andamio_AG.jpg");
            agencyAndamio.setBankAccountNumber("VIN-005");
            userRepo.save(agencyAndamio);

            //Creación de producto ofrecido por Agencia Número 1
            Event concierto = new Event(100, 2500D, "30", "601 Biscayne Blvd, Miami, FL, Estados Unidos", agencyAndamio, "Maluma", "El mejor concierto de tu vida", 1000, "IMG-URL", "Maluma Fire Tour", true);
            concierto.setUser(agencyAndamio);
            concierto.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821034/Services/Activities/nicolas-flor-h5XPfJFQgFM-unsplash_iaz0jc.jpg");
            productRepo.save(concierto);

            concierto = new Event(100, 2500D, "20", "601 Biscayne Blvd, California, Estados Unidos", agencyAndamio, "David Guetta World Tour", "El mejor concierto de tu vida", 1000, "", "David Guetta", true);
            concierto.setUser(agencyAndamio);
            concierto.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821030/Services/Activities/pexels-tom-fisk-1692695_lwwuxu.jpg");
            productRepo.save(concierto);
            concierto = new Event(100, 2500D, "10", "Bucharest, Romania", agencyAndamio, "Mountain long-hiking", "Hiking in the Carpats, Romania", 1000, "https://res.cloudinary.com/melbastrips/image/upload/v1638821029/Services/Activities/success-dhamala-JcZvtztsLm8-unsplash_per91e.jpg", "Hiking", true);
            concierto.setUser(agencyAndamio);
            concierto.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821029/Services/Activities/success-dhamala-JcZvtztsLm8-unsplash_per91e.jpg");
            productRepo.save(concierto);
            UserProduct offeredProduct = new UserProduct(); //Al instanciarlos como UserProduct se persisten también en la tabla User_Product,
            // sino, si lo instanciamos como ClientEvent o ClientTicket o HotelTicket se guardan en las tablas de SoldEvents,SoldHotels o SoldTickets
            offeredProduct.setProduct(concierto);
            offeredProduct.setUser(agencyAndamio);
            salesRepos.save(offeredProduct);

            //Creación de producto ofrecido por agencia Número 2
            Hotel hotel2 = new Hotel(100, 2500D, "5", "2901 Collins Ave, Miami Beach, FL 33140, Estados Unidos", agencyAndamio, "The Miami Beach Edition", "The Miami Beach EDITION is a boutique, design hotel with modern rooms and luxury amenities", 500, "IMG-URL", true, true, 200, null);
            UserProduct offeredProduct2 = new UserProduct(agencyAndamio, hotel2);
            hotel2.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821036/Services/Lodging/valeriia-bugaiova-_pPHgeHz1uk-unsplash_qz1wyt.jpg");
            productRepo.save(hotel2);
            salesRepos.save(offeredProduct2);

            ////Creación de Agencia
            User agencyBabel = new User("Babel", "Viajes", "babel@agency.com", passwordEncoder.encode("12345678"), UserRole.AGENCY);
            agencyBabel.setBankAccountNumber("VIN-007");
            agencyBabel.setAddress("San Martín 1136 (Pasaje San Martín) Local 33, Mendoza");
            agencyBabel.setImgUrl("https://res.cloudinary.com/diyps0xa6/image/upload/v1638713829/Ecommerce/profile_pics/babel_AG.jpg");
            userRepo.save(agencyBabel);


            Hotel hospedaje = new Hotel(500, 3500D, "10", "Av. de Fransesc Cambó, 14, 08003, Barcelona, España", agencyAndamio, "The Barcelona Edition", "The Barcelona EDITION offers boutique style, personalized service, 5-star luxury hotel amenities and a city center setting for your visit to Spain.", 500, "IMG-URL", true, true, 200, null);
            UserProduct offeredProduct3 = new UserProduct(agencyBabel, hospedaje);
            hospedaje.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638988475/Services/Lodging/wjssbxpoddizvawrttn4.jpg");
            productRepo.save(hospedaje);
            salesRepos.save(offeredProduct3);

            Hotel hospedaje5 = new Hotel(500, 3500D, "30", "av. de Fransesc Cambó, 14, 08003, Barcelona, España", agencyAndamio, "The Abu Dhabi Edition", "Vas a quedar BarceLove pipicucu", 500, "IMG-URL", true, true, 200, null);
            UserProduct offeredProduct5 = new UserProduct(agencyBabel, hospedaje5);
            hospedaje5.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821030/Services/Lodging/roberto-nickson-emqnSQwQQDo-unsplash_laca9v.jpg");
            productRepo.save(hospedaje5);
            salesRepos.save(offeredProduct5);

            Hotel hospedaje6 = new Hotel(500, 3500D, "20", "av. de Fransesc Cambó, 14, 08003, Barcelona, España", agencyAndamio, "The New York Edition", "Vas a quedar BarceLove pipicucu", 500, "IMG-URL", true, true, 200, null);
            UserProduct offeredProduct6 = new UserProduct(agencyBabel, hospedaje6);
            hospedaje6.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821029/Services/Lodging/hotel1_qvgxv4.jpg");
            productRepo.save(hospedaje6);
            salesRepos.save(offeredProduct6);


            Hotel hospedaje2 = new Hotel(150, 1500D, "40", "Av. Arístides Villanueva 385, M5500EOW Mendoza", agencyBabel, "Chill Inn Hostel", "Relax en Mendolandia", 500, "IMG-URL", false, false, 200, Pension.BREAKFAST_BUFFET);
            UserProduct offeredProduct4 = new UserProduct(agencyBabel, hospedaje2);
            hospedaje2.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821030/Services/Lodging/hotel3_dwhtwa.jpg");
            productRepo.save(hospedaje2);
            salesRepos.save(offeredProduct4);

            User clientMelba = new User("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melba123!"), UserRole.CLIENT);
            clientMelba.setImgUrl("https://res.cloudinary.com/diyps0xa6/image/upload/v1638713199/Ecommerce/profile_pics/melba_CL.jpg");
            clientMelba.setBankAccountNumber("VIN-003");
            userRepo.save(clientMelba);

            User clientRicardo = new User("Ricardo", "Morel", "ricardo@mindhub.com", passwordEncoder.encode("ricardo123!"), UserRole.CLIENT);
            clientRicardo.setImgUrl("https://res.cloudinary.com/diyps0xa6/image/upload/v1638713630/Ecommerce/profile_pics/ricardoM_CL.jpg");
            clientRicardo.setBankAccountNumber("VIN-003");
            userRepo.save(clientRicardo);


//            ClientHotel melbaHotel = new ClientHotel(clientMelba, hospedaje, LocalDateTime.now(), LocalDateTime.now().plusDays(5), 5);
//            melbaHotel.setPension(Pension.BREAKFAST_BUFFET);
//            melbaHotel.setQuantity(2);
//            melbaHotel.setFinalPrice(hospedaje.getPrice());
            Ticket ticket = new Ticket(2000, 15000D, "10", "08820 El Prat de Llobregat, Barcelona, España", agencyAndamio, "Vuelo Barcelona - Madrid", "El vuelo más copado de tu laif", 100, "https://res.cloudinary.com/melbastrips/image/upload/v1638981253/Services/Flies/arquitectura_gaudi_sag_fam_valery_egonov_portada_z78brl.jpg", LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Madrid", "Barcelona");
            productRepo.save(ticket);
            ticket = new Ticket(2000, 18000D, "20", "95700 Roissy-en-France, Francia", agencyAndamio, "Vuelo a Paris", "El vuelo más copado de tu laif", 100, "https://res.cloudinary.com/melbastrips/image/upload/v1638821033/Services/Flies/kateryna-t-RkyYEVHrRbo-unsplash_wzahhy.jpg", LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Anywhere", "Paris");
            productRepo.save(ticket);
            ticket = new Ticket(2000, 20000D, "30", "08820 El Prat de Llobregat, Barcelona, España", agencyAndamio, "Vuelo Barcelona - Madrid", "El vuelo más copado de tu laif", 100, "https://res.cloudinary.com/melbastrips/image/upload/v1638817782/Services/Flies/Machu_Picchu_Per%C3%BA_xker0x.jpg", LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Buenos Aires", "Machu Pichu");
            productRepo.save(ticket);
//            ClientTicket cl = new ClientTicket(clientMelba, ticket, TicketClass.FIRST);
//            cl.setQuantity(2);
//
//            // cl.setUser(clientMelba);
//            cl.setUserHistory(clientMelba);
//            cl.setFinalPrice(ticket.getPrice());
  //         salesRepos.save(cl);
//            salesRepos.save(melbaHotel);
//
//            ClientEvent melbaConcert = new ClientEvent(clientMelba, concierto, true);
//            melbaConcert.setQuantity(1);

            //melbaConcert.setUser(clientMelba);
//            melbaConcert.setUserHistory(clientMelba);
//            melbaConcert.setFinalPrice(concierto.getPrice());
//            salesRepos.save(melbaConcert);

            ClientHotel ricardoHotel = new ClientHotel(clientRicardo, hospedaje2, LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(7), 2);
            ricardoHotel.setPension(Pension.BREAKFAST_BUFFET);
            ricardoHotel.setQuantity(1);
            ricardoHotel.setFinalPrice(hospedaje2.getPrice());
            salesRepos.save(ricardoHotel);

            userRepo.save(clientMelba);
            userRepo.save(clientRicardo);
            userRepo.save(agencyAndamio);
            userRepo.save(agencyBabel);


        };
    }
}
