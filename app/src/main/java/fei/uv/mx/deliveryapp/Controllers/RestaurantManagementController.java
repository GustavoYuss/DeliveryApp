package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.*;
import fei.uv.mx.deliveryapp.Security.JwtAuthenticationFilter;
import fei.uv.mx.deliveryapp.Services.implementations.DishServices;
import fei.uv.mx.deliveryapp.Services.implementations.RestaurantServices;
import fei.uv.mx.deliveryapp.Services.implementations.UserServices;
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

import java.util.List;

@Controller
public class RestaurantManagementController {

    private int idRestaurant;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Autowired
    private DishServices dishServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private RestaurantServices restaurantServices;

    @GetMapping("/restaurantManagement")
    public String restaurantManagementPage(HttpServletRequest request, Model model) {
        getIdRestaurant(request);
        Dish newDish = new Dish();
        newDish.setDishType(new DishType());

        List<Dish> dishes = dishServices.getDishesByRestaurantId(idRestaurant);
        List<DishType> dishTypeList = restaurantServices.getDishesTypeByRestaurant(idRestaurant);
        List<OrderRestaurant> orders = restaurantServices.getOrderRestaurantByRestaurant(idRestaurant);

        model.addAttribute("dish", newDish);
        model.addAttribute("dishesMenu", dishes);
        model.addAttribute("dishTypeList", dishTypeList);
        model.addAttribute("orders", orders);

        System.out.println(idRestaurant);
        System.out.println(orders.size());

        return "restaurantManagement";
    }

    @PostMapping("/addDishToMenu")
    public ResponseEntity<String> addDishToMenu(@ModelAttribute Dish dish) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(idRestaurant);
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
        System.out.println(dishes.size());
        return ResponseEntity.ok(dishes);
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
                    idRestaurant = restaurantServices.getRestaurantByIdUser(userID);
                }
            }
        }
    }
}
