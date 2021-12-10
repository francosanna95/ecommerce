package com.mindhub.ecommerce.services;

import com.itextpdf.layout.Document;
import com.mindhub.ecommerce.dtos.UserDTO;
import com.mindhub.ecommerce.dtos.UserProductDTO;
import com.mindhub.ecommerce.email.EmailServiceImpl;
import com.mindhub.ecommerce.enums.Pension;
import com.mindhub.ecommerce.enums.TicketClass;
import com.mindhub.ecommerce.enums.UserRole;
import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.SalesRepository;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SalesRepository salesRepo;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, passwordEncoder.encode(password), UserRole.CLIENT);
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean createAgency(String fantasyName, String email, String password, String imgUrl, String address) {
        User user = new User(fantasyName, fantasyName, email, passwordEncoder.encode(password), UserRole.AGENCY);
        user.setImgUrl(imgUrl);
        user.setAddress(address);
        userRepo.save(user);
        return true;
    }

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
        return userRepo.findById(id).map(UserDTO::new).orElse(null);
    }

    @Override
    public boolean addEventToClientCart(User user, Event event, Boolean isVip, Integer attendants) {

        try {
// si el cliente ya tiene ese producto en el carrito simplemente actualizo la cantidad y el precio final
            for (UserProduct userProduct : user.getCurrentCart()) {
                if (Objects.equals(userProduct.getProduct().getProductId(), event.getProductId())) {
                    ClientEvent eventToCart = (ClientEvent) userProduct;
                    eventToCart.setQuantity(userProduct.getQuantity() + attendants);
                    eventToCart.setFinalPrice(event.getPrice());
                    int eventStock = event.getStock();
                    event.setStock(eventStock - attendants);
                    userRepo.save(user);
                    productRepo.save(event);
                    return true;
                }
            }
//sino creo una nueva instancia de esa venta
            ClientEvent clientEvent = new ClientEvent();
            clientEvent.setUser(user);
            clientEvent.setQuantity(attendants);
            clientEvent.setVip(isVip);
            clientEvent.setProduct(event);
            clientEvent.setFinalPrice(event.getPrice());
            int eventStock = event.getStock();
            event.setStock(eventStock - attendants);
            userRepo.save(user);
            productRepo.save(event);
            return true;

        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public UserProductDTO addTicketToClientCart(User user, Ticket ticket, String clase, Integer passangers) {

        try {
// si el cliente ya tiene ese producto en el carrito simplemente actualizo la cantidad y el precio final

            for (UserProduct userProduct : user.getCurrentCart()) {
                if (Objects.equals(userProduct.getProduct().getProductId(), ticket.getProductId())) {
                    ClientTicket ticketToCart = (ClientTicket) userProduct;
                    ticketToCart.setQuantity(userProduct.getQuantity() + passangers);
                    ticketToCart.setFinalPrice(ticket.getPrice());
                    salesRepo.save(ticketToCart);
                    UserProductDTO userProductDTO=new UserProductDTO(ticketToCart);
                    userRepo.save(user);
                    productRepo.save(ticket);
                    return userProductDTO;
                }
            }
//sino creo una nueva instancia de esa venta
            ClientTicket clientTicket = new ClientTicket(user,ticket,TicketClass.valueOf(clase));
            clientTicket.setQuantity(passangers);
            clientTicket.setFinalPrice(ticket.getPrice());
            int ticketStock = ticket.getStock();
            ticket.setStock(ticketStock - passangers);
            user.getCurrentCart().add(clientTicket);
            salesRepo.save(clientTicket);
            UserProductDTO userProductDTO=new UserProductDTO(clientTicket);
            userRepo.save(user);
            productRepo.save(ticket);
            return userProductDTO;

        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public boolean addHotelToClientCart(User user, Hotel hotel, String arrivalDate, String departureDate, Integer
            nights, Integer passangers, String pension) {
        LocalDateTime arrival = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime departure = LocalDateTime.parse(departureDate, DateTimeFormatter.ISO_DATE_TIME);
        Pension pensionChoice = Pension.valueOf(pension);

        // si el cliente ya tiene ese producto en el carrito simplemente actualizo la cantidad y el precio final

        try {
            for (UserProduct userProduct : user.getCurrentCart()) {
                if (Objects.equals(userProduct.getProduct().getProductId(), hotel.getProductId())) {
                    ClientHotel hotelToCart = (ClientHotel) userProduct;
                    hotelToCart.setPension(pensionChoice);
                    hotelToCart.setUser(user);
                    hotelToCart.setQuantity(passangers);
                    hotelToCart.setProduct(hotel);
                    hotelToCart.setFinalPrice(hotel.getPrice());
                    int hotelStock = hotel.getStock();
                    hotel.setStock(hotelStock - passangers);
                    //todo agregar salesRepo
                    userRepo.save(user);
                    productRepo.save(hotel);
                    return true;
                }
            }
            //sino creo una nueva instancia de esa venta

            ClientHotel clientHotel = new ClientHotel();
            clientHotel.setPension(pensionChoice);
            clientHotel.setUser(user);
            clientHotel.setQuantity(passangers);
            clientHotel.setProduct(hotel);
            clientHotel.setFinalPrice(hotel.getPrice());
            int hotelStock = hotel.getStock();
            hotel.setStock(hotelStock - passangers);
            userRepo.save(user);
            productRepo.save(hotel);
            return true;
        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public boolean add1ProductToCart(User user, UserProduct toAdd) {
        boolean success = false;
        for (UserProduct userProduct : user.getCurrentCart()) {
            if (userProduct.getId().equals(toAdd.getId())) {
                int currentQuantity = userProduct.getQuantity();
                userProduct.setQuantity(currentQuantity + 1); // actualizo la cantidad
                userProduct.setFinalPrice(userProduct.getProduct().getPrice()); // actualizo el precio
                success = true;
                Product product = userProduct.getProduct(); // actualizo stock del producto
                int currentStock = product.getStock();
                product.setStock(currentStock - 1);
                productRepo.save(product);
                userRepo.save(user);
                salesRepo.save(userProduct);
                break;
            }

        }
        if (success) return true;

        return false;

    }

    @Override
    public boolean removeProductFromCart(User user, UserProduct toDelete) {
        boolean success = false;
        for (UserProduct userProduct : user.getCurrentCart()) {
            if (Objects.equals(userProduct.getId(), toDelete.getId())) {
                if (userProduct.getQuantity() == 1) { // si es el último producto de ese tipo en el carrito
                    success = finalRemoveProductFromCart(user, toDelete);
                } else {
                    int currentQuantity = userProduct.getQuantity();
                    userProduct.setQuantity(currentQuantity - 1); // actualizo la cantidad
                    userProduct.setFinalPrice(userProduct.getProduct().getPrice()); // actualizo el precio
                    success = true;
                }
                Product product = userProduct.getProduct(); // actualizo stock del producto
                int currentStock = product.getStock();
                product.setStock(currentStock + 1);
                productRepo.save(product);
                userRepo.save(user);
                break;
            }
        }


        if (success) return true;

        return false;


    }

    public boolean finalRemoveProductFromCart(User user, UserProduct toDelete) {
        boolean success = user.getCurrentCart().remove(toDelete);
        salesRepo.delete(toDelete);
        return success;
    }

    @Override
    public boolean sendInvoice(User user, byte[] bytes) {
        String email = emailServiceImpl.createEmail(user.getFirstName(), user.getLastName());
        emailServiceImpl.send(user.getEmail(), email, bytes);
        return true;
    }


}
