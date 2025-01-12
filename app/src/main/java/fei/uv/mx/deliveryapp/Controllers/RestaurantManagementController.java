package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.DishOrderDTO;
import fei.uv.mx.deliveryapp.DTOs.RestaurantRequestDTO;
import fei.uv.mx.deliveryapp.Models.*;
import fei.uv.mx.deliveryapp.Repositories.DishTypeRepository;
import fei.uv.mx.deliveryapp.Repositories.RestaurantDishTypeRepository;
import fei.uv.mx.deliveryapp.Services.implementations.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Controller
public class RestaurantManagementController {

    private Restaurant restaurant;

    private List<OrderRestaurant> orders;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Autowired
    private DishServices dishServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private RestaurantServices restaurantServices;

    @Autowired
    private OrderDishServices orderServices;

    @Autowired
    private DishTypeRepository dishTypeRepository;

    @Autowired
    private RestaurantDishTypeRepository restaurantDishTypeRepository;

    @Autowired
    private OrderServices orderServicesGeneral;


    @GetMapping("/restaurantManagement")
    public String restaurantManagementPage(HttpServletRequest request, Model model) {
        getIdRestaurant(request);

        if(restaurant != null)
        {
            Dish newDish = new Dish();
            newDish.setDishType(new DishType());
            List<Dish> dishes = dishServices.getDishesByRestaurantId(restaurant.getId());
            List<DishType> dishTypeList = restaurantServices.getDishesTypeByRestaurant(restaurant.getId());
            orders = restaurantServices.getOrderRestaurantByRestaurant(restaurant.getId());
            double earnings = getTotalEarnings(orders);
            int finishedOrders = getCompleteOrders(orders);

            model.addAttribute("dish", newDish);
            model.addAttribute("dishesMenu", dishes);
            model.addAttribute("dishTypeList", dishTypeList);
            model.addAttribute("orders", orders);
            model.addAttribute("restaurant", restaurant);
            model.addAttribute("earnings", earnings);
            model.addAttribute("finishedOrders", finishedOrders);
            model.addAttribute("startDate", LocalDate.now());
            model.addAttribute("endDate", LocalDate.now());
            model.addAttribute("tomorrow", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy")));

            return "restaurantManagement";
        }
        else {
            List<DishType> dishTypeList = dishTypeRepository.findAll();
            System.out.println("Numero de elementod: " + dishTypeList.stream().count());
            model.addAttribute("dishTypeList", dishTypeList); // Debe coincidir con el atributo usado en la vista
            return "createRestaurant";
        }
    }

    @PostMapping("/registerRestaurant")
    public ResponseEntity<?> registerRestaurant(@RequestBody @Valid RestaurantRequestDTO requestDTO) {
        try {
            Restaurant newRestaurant = new Restaurant();
            newRestaurant.setNameRestaurant(requestDTO.getNameRestaurant());
            newRestaurant.setCloseTime(requestDTO.getCloseTime());
            newRestaurant.setOpenTime(requestDTO.getOpenTime());
            newRestaurant.setImagePath(requestDTO.getImagePath());
            newRestaurant.setImageLogoPath(requestDTO.getImageLogoPath());
            newRestaurant.setRating(BigDecimal.ZERO);
            newRestaurant.setUser(requestDTO.getUser());

            Restaurant restaurantConfirm = restaurantServices.createRestaurant(newRestaurant);

            if (restaurantConfirm != null) {
                requestDTO.getCategories().forEach(category -> {
                    RestaurantDishType restaurantDishType = new RestaurantDishType();
                    restaurantDishType.setIdRestaurant(restaurantConfirm.getId());
                    restaurantDishType.setIdDishType(category);
                    restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType);
                });

                return ResponseEntity.ok("Registro Correcto");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo registrar el restaurante");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el restaurante: " + e.getMessage());
        }
    }

    @PostMapping("/addDishToMenu")
    public ResponseEntity<String> addDishToMenu(@ModelAttribute Dish dish) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(this.restaurant.getId());
            dish.setRestaurant(restaurant);
            dishServices.createDish(dish);
            return ResponseEntity.ok("restaurantManagement");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding dish");
        }
    }

    @GetMapping("/getDishesFromOrder")
    public ResponseEntity<List<DishOrderDTO>> getDishesFromOrder(HttpServletRequest request) {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        List<DishOrderDTO> dishes = restaurantServices.getDishesFromOrderRestaurant(idOrder);
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/updateStatusOrder")
    public ResponseEntity<String> updateStatusOrder(HttpServletRequest request) {
        int idStatus = Integer.parseInt(request.getParameter("idStatus"));
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        OrderRestaurant orderSelected = new OrderRestaurant();
        for (OrderRestaurant order : orders) {
            if (order.getId() == idOrder) {
                orderSelected = order;
            }
        }
        Status status = new Status();
        status.setId(idStatus);
        Order order = orderServicesGeneral.getIdOrderGeneral(orderSelected.getId());
        order.setStatus(status);
        orderSelected.setIdStatus(status);
        orderServices.updateOrderDish(orderSelected);
        return ResponseEntity.ok("restaurantManagement");
    }

    @GetMapping ("/getTodayStats")
    public ResponseEntity<List<Integer>> getTodayStats(HttpServletRequest request) {
        List<Integer> todayStats = new LinkedList<>();
        LocalDate date = LocalDate.now();
        todayStats.add(restaurantServices.getCountOrderByStatusAndDate(restaurant.getId(),1, date));
        todayStats.add(restaurantServices.getCountOrderByStatusAndDate(restaurant.getId(),2, date));
        todayStats.add(restaurantServices.getCountOrderByStatusAndDate(restaurant.getId(),3, date));
        todayStats.add(restaurantServices.getCountOrderByStatusAndDate(restaurant.getId(),4, date));
        System.out.println(todayStats.size());
        return ResponseEntity.ok(todayStats);
    }

    @GetMapping ("/getStats")
    public ResponseEntity<List<Integer>> getStats(HttpServletRequest request) {
        List<Integer> stats = new LinkedList<>();
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),1));
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),2));
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),3));
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),4));
        return ResponseEntity.ok(stats);
    }

    private void getIdRestaurant(HttpServletRequest request) {
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
                    int userID = userServices.getUserByEmail(name);
                    restaurant = restaurantServices.getRestaurantByIdUser(userID);
                }
            }
        }
    }

    private double getTotalEarnings(List<OrderRestaurant> orders) {
        double totalEarnings = 0;
        for (OrderRestaurant order : orders) {
            if (order.getIdStatus().getId() == 3) {
                totalEarnings += order.getTotal();
            }
        }
        return totalEarnings;
    }

    private int getCompleteOrders(List<OrderRestaurant> orders) {
        int completeOrders = 0;
        for (OrderRestaurant order : orders) {
            if (order.getIdStatus().getId() == 3) {
                completeOrders++;
            }
        }
        return completeOrders;
    }

    @GetMapping("/filter")
    public String filterOrders(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        if (startDate != null && endDate != null && restaurant != null) {
            Dish newDish = new Dish();
            newDish.setDishType(new DishType());
            List<Dish> dishes = dishServices.getDishesByRestaurantId(restaurant.getId());
            List<DishType> dishTypeList = restaurantServices.getDishesTypeByRestaurant(restaurant.getId());
            List<OrderRestaurant> orders = restaurantServices.getOrderRestaurantByDay(restaurant.getId(),startDate, endDate);
            double earnings = getTotalEarnings(orders);
            int finishedOrders = getCompleteOrders(orders);

            model.addAttribute("dish", newDish);
            model.addAttribute("dishesMenu", dishes);
            model.addAttribute("dishTypeList", dishTypeList);
            model.addAttribute("orders", orders);
            model.addAttribute("restaurant", restaurant);
            model.addAttribute("earnings", earnings);
            model.addAttribute("finishedOrders", finishedOrders);
            model.addAttribute("startDate", startDate != null ? startDate : LocalDate.now());
            model.addAttribute("endDate", endDate != null ? endDate : LocalDate.now());
        }
        return "restaurantManagement";
    }

}
