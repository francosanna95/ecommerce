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

            //Creación de producto ofrecido por Agencia Número 1
            Event concierto = new Event(100, 2500D, "5", "601 Biscayne Blvd, Miami, FL, Estados Unidos", agencyAndamio, "Maluma", "El mejor concierto de tu vida", 1000, "IMG-URL", "Maluma Fire Tour", true);
            concierto.setUser(agencyAndamio);
            concierto.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821030/Services/Activities/pexels-tom-fisk-1692695_lwwuxu.jpg");
            UserProduct offeredProduct = new UserProduct(); //Al instanciarlos como UserProduct se persisten también en la tabla User_Product,
            // sino, si lo instanciamos como ClientEvent o ClientTicket o HotelTicket se guardan en las tablas de SoldEvents,SoldHotels o SoldTickets
            offeredProduct.setProduct(concierto);
            offeredProduct.setUser(agencyAndamio);

            userRepo.save(agencyAndamio);
            productRepo.save(concierto);
            salesRepos.save(offeredProduct);

            //Creación de producto ofrecido por agencia Número 2
            Hotel hotel2 = new Hotel(100, 2500D, "5", "2901 Collins Ave, Miami Beach, FL 33140, Estados Unidos", agencyAndamio, "The Miami Beach Edition", "La mejor estadia en Miami de tu laif", 500, "IMG-URL", true, true, 200, null);
            UserProduct offeredProduct2 = new UserProduct(agencyAndamio, hotel2);
            hotel2.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821030/Services/Activities/roberto-nickson-emqnSQwQQDo-unsplash_laca9v.jpg");
            productRepo.save(hotel2);
            salesRepos.save(offeredProduct2);

            ////Creación de Agencia
            User agencyBabel = new User("Babel", "Viajes", "babel@agency.com", passwordEncoder.encode("12345678"), UserRole.AGENCY);
            agencyBabel.setBankAccountNumber("VIN-007");
            agencyBabel.setAddress("San Martín 1136 (Pasaje San Martín) Local 33, Mendoza");
            agencyBabel.setImgUrl("https://res.cloudinary.com/diyps0xa6/image/upload/v1638713829/Ecommerce/profile_pics/babel_AG.jpg");
            userRepo.save(agencyBabel);


            Hotel hospedaje = new Hotel(500, 3500D, "10", "av. de Fransesc Cambó, 14, 08003, Barcelona, España", agencyAndamio, "The Barcelona Edition", "Vas a quedar BarceLove pipicucu", 500, "IMG-URL", true, true, 200, null);
            UserProduct offeredProduct3 = new UserProduct(agencyBabel, hospedaje);
            hospedaje.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821036/Services/Activities/valeriia-bugaiova-_pPHgeHz1uk-unsplash_qz1wyt.jpg");
            productRepo.save(hospedaje);
            salesRepos.save(offeredProduct3);


            Hotel hospedaje2 = new Hotel(150, 1500D, "NONE", "Av. Arístides Villanueva 385, M5500EOW Mendoza", agencyBabel, "Chill Inn Hostel", "Relax en Mendolandia", 500, "IMG-URL", false, false, 200, Pension.BREAKFAST_BUFFET);
            UserProduct offeredProduct4 = new UserProduct(agencyBabel, hospedaje2);
            hospedaje2.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821035/Services/Activities/saad-khan-425b2PhNuHA-unsplash_w5csr7.jpg");
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


            ClientHotel melbaHotel = new ClientHotel(clientMelba, hospedaje, LocalDateTime.now(), LocalDateTime.now().plusDays(5), 5, 2);
            melbaHotel.setPension(Pension.BREAKFAST_BUFFET);

            Ticket ticket = new Ticket(2000, 20000D, "ALMUNDO", "08820 El Prat de Llobregat, Barcelona, España", agencyAndamio, "Vuelo Barcelona - Madrid", "El vuelo más copado de tu laif", 100, "https://res.cloudinary.com/melbastrips/image/upload/v1638817782/Services/Flies/Machu_Picchu_Per%C3%BA_xker0x.jpg", LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Barcelona", "Madrid");
            ticket.setImgUrl("https://res.cloudinary.com/melbastrips/image/upload/v1638821028/Services/Activities/thais-cordeiro-MUDP2jIK0IY-unsplash_f3rurz.jpg");
            productRepo.save(ticket);
            ClientTicket cl = new ClientTicket(clientMelba, ticket, TicketClass.FIRST, 2);

            // cl.setUser(clientMelba);
            cl.setUserHistory(clientMelba);
            salesRepos.save(cl);
            salesRepos.save(melbaHotel);

            ClientEvent melbaConcert = new ClientEvent(clientMelba, concierto, true, 2);

            //melbaConcert.setUser(clientMelba);
            melbaConcert.setUserHistory(clientMelba);
            salesRepos.save(melbaConcert);

            ClientHotel ricardoHotel = new ClientHotel(clientRicardo, hospedaje2, LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(7), 2, 1);
            ricardoHotel.setPension(Pension.BREAKFAST_BUFFET);
            salesRepos.save(ricardoHotel);

            userRepo.save(clientMelba);
            userRepo.save(clientRicardo);
            userRepo.save(agencyAndamio);
            userRepo.save(agencyBabel);


        };
    }
}
