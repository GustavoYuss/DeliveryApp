package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.*;
import fei.uv.mx.deliveryapp.Services.implementations.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
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


    @GetMapping("/restaurantManagement")
    public String restaurantManagementPage(HttpServletRequest request, Model model) {
        getIdRestaurant(request);
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

        return "restaurantManagement";
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
        OrderRestaurant orderSelected = null;
        for (OrderRestaurant order : orders) {
            if (order.getId() == idOrder) {
                orderSelected = order;
            }
        }
        Status status = new Status();
        status.setId(idStatus);
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
        System.out.println(todayStats.size());
        return ResponseEntity.ok(todayStats);
    }

    @GetMapping ("/getStats")
    public ResponseEntity<List<Integer>> getStats(HttpServletRequest request) {
        List<Integer> stats = new LinkedList<>();
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),1));
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),2));
        stats.add(restaurantServices.getCountOrderByStatus(restaurant.getId(),3));
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
            if (order.getIdStatus().getId() == 2) {
                totalEarnings += order.getTotal();
            }
        }
        return totalEarnings;
    }

    private int getCompleteOrders(List<OrderRestaurant> orders) {
        int completeOrders = 0;
        for (OrderRestaurant order : orders) {
            if (order.getIdStatus().getId() == 2) {
                completeOrders++;
            }
        }
        return completeOrders;
    }
}
