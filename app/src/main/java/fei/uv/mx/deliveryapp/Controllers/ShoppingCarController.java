package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.DTOs.OrderDTO;
import fei.uv.mx.deliveryapp.DTOs.OrderDishDTO;
import fei.uv.mx.deliveryapp.Repositories.CustomerCarRepository;
import fei.uv.mx.deliveryapp.Repositories.OrderAppRestaurantRepository;
import fei.uv.mx.deliveryapp.Repositories.OrderRestaurantDishRepository;
import fei.uv.mx.deliveryapp.Repositories.OrderRestaurantRepository;
import fei.uv.mx.deliveryapp.Services.implementations.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fei.uv.mx.deliveryapp.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/deliveryApp/shoppingCar")
public class ShoppingCarController {

    private int userID;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Autowired
    private UserServices userServices;

    @Autowired
    private OrderServices orderServices;

    @Autowired
    private PaymentServices paymentServices;

    @Autowired
    private LocationServices locationServices;

    @Autowired
    private CustomerCarRepository customerCarRepository;

    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;

    @Autowired
    private OrderRestaurantDishRepository orderDishRepository;

    @Autowired
    private OrderAppRestaurantRepository orderAppRepository;

    @GetMapping("/")
    public String carPage(HttpServletRequest request, Model model) {
        OrderDTO orderDTO = new OrderDTO();
        ArrayList<OrderDishDTO> dishes = new ArrayList<OrderDishDTO>();
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("dishes", dishes);
        return "shoppingCar";
    }

    @PostMapping("/makeOrder")
    public void makeOrder(HttpServletRequest request, Model model) {
        getIdUser(request);
        User user = new User();
        user.setId(userID);
        List<CustomerCart> customerCartList = orderServices.getCustomerCart(userID);
        //int idUserAddress = locationServices.getAddressByUser(userID);
        //Payment idUserPayment = paymentServices.getPaymentsByUserId(userID);
        Order order = new Order();
        order.address = "PUTOS TODOS";
        Address address = new Address();
        order.setDate(LocalDate.now());
        order.setTotal(new BigDecimal("123.45"));
        //order.setIdPayment(idUserPayment);
        order.setIdUser(user);
        order = orderServices.createOrder(order);
        List<Integer> idRestaurntsList = getIdRestaurant(customerCartList);
        registerOrder(customerCartList, idRestaurntsList, order);
    }

    private List<Integer> getIdRestaurant(List<CustomerCart> customerCartList) {
        List<Integer> idRestaurantList = new ArrayList<>();
        for (CustomerCart customerCart : customerCartList) {
            if(!idRestaurantList.contains(customerCart.getDish().getId())){
                idRestaurantList.add(customerCart.getDish().getRestaurant().getId());
            }
        }

        return idRestaurantList;
    }

    private void registerOrder(List<CustomerCart> customerCartList, List<Integer> idRestaurantList, Order order) {
        for (int idRestaurant : idRestaurantList) {
            OrderRestaurant orderRestaurant = new OrderRestaurant();
            orderRestaurant.setDate(LocalDate.now());
            Status status = new Status();
            status.setId(1);
            orderRestaurant.setIdStatus(status);
            User user = new User();
            user.setId(userID);
            orderRestaurant.setIdUser(user);
            orderRestaurant = orderRestaurantRepository.createOrderRestaurant(orderRestaurant);
            for (CustomerCart customerCart : customerCartList) {
                if (customerCart.getUser().getId() == user.getId()) {
                    OrderRestaurantDish orderRestaurantDish = new OrderRestaurantDish();
                    orderRestaurantDish.setIdDish(customerCart.getDish());
                    orderRestaurantDish.setIdOrderRestaurant(orderRestaurant);
                    orderRestaurantDish.setAmount(customerCart.getQuantity());
                    orderDishRepository.save(orderRestaurantDish);
                }
            }
            OrderAppRestaurant orderAppRestaurant = new OrderAppRestaurant();
            orderAppRestaurant.setIdOrder(order);
            orderAppRestaurant.setIdOrderRestaurant(orderRestaurant);
            Restaurant restaurant = new Restaurant();
            restaurant.setId(idRestaurant);
            orderAppRestaurant.setIdRestaurant(restaurant);
            orderAppRepository.save(orderAppRestaurant);
        }
    }


    private void getIdUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(jwtSecret.getBytes())
                            .build()
                            .parseClaimsJws(cookie.getValue())
                            .getBody();
                    String name = claims.getSubject();
                    userID = userServices.getUserByEmail(name);
                }
            }
        }
    }

    @GetMapping("/getItems")
    public ResponseEntity<?> getCustomerCar(@RequestParam("id") Integer userID) {
        if (userID == null) {
            return ResponseEntity.badRequest().body("User ID is required");
        }

        try {
            List<CustomerCart> dishes = customerCarRepository.findByCustomerId(userID);
            return ResponseEntity.ok(dishes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching restaurants");
        }
    }

    @GetMapping("/getQuantityItems")
    public ResponseEntity<Integer> getQuantity(@RequestParam("id") int userID) {
        try {
            int items = customerCarRepository.countByCustomer(userID);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

    @DeleteMapping("/RemoveDishCart")
    public ResponseEntity<Boolean> removeDishFromCart(@RequestParam("id") int customerCarID) {
        try {
            boolean result = customerCarRepository.deleteCustomerCart(customerCarID);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/DecreaseDishCart")
    public ResponseEntity<Boolean> decreaseDishFromCart(@RequestParam("id") int customerCarID) {
        System.out.println("Recibido ID: " + customerCarID);
        try {
            CustomerCart customerCart = customerCarRepository.getCustomerCartsById(customerCarID);
            if (customerCart != null) {
                customerCart.setQuantity(customerCart.getQuantity() - 1);
                boolean result;
                if (customerCart.getQuantity() < 1) {
                    result = customerCarRepository.deleteCustomerCart(customerCarID);
                } else {
                    result = customerCarRepository.updateCustomerCart(customerCart);
                }
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(false);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
