package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.CustomerCart;
import fei.uv.mx.deliveryapp.Models.Dish;
import fei.uv.mx.deliveryapp.Models.Restaurant;
import fei.uv.mx.deliveryapp.Models.User;
import fei.uv.mx.deliveryapp.Repositories.CustomerCarRepository;
import fei.uv.mx.deliveryapp.Repositories.DishRepository;
import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/deliveryApp/restaurants")
public class RestaurantDetailsController {

    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final DishRepository dishRepository;
    @Autowired
    private final CustomerCarRepository customerCartRepository;
    @Autowired
    private final UserRepository userRepository;

    public RestaurantDetailsController(RestaurantRepository restaurantRepository, DishRepository dishRepository, CustomerCarRepository customerCartRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.customerCartRepository = customerCartRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/showDetails")
    public String showRestaurantDetails(@RequestParam("id") int restaurantId, Model model) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            model.addAttribute("restaurant", restaurant);
        }
        return "restaurantDetails";
    }

    @GetMapping("/getDishesByRestaurant")
    public ResponseEntity<List<Dish>> getDishesByRestaurant(@RequestParam("id") int restaurantId) {
        System.out.println("Si se llego a llamar el meotodo");
        List<Dish> dishList = dishRepository.findByRestaurantId(restaurantId);
        if (dishList == null || dishList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dishList);
    }

    @PostMapping("/addDishToCar")
    public ResponseEntity<?> addDishToCart(@RequestBody CustomerCart customerCart) {
        try {
            User user = userRepository.findById(1).orElseThrow(() -> new Exception("Usuario no encontrado"));
            customerCart.setUser(user);
            System.out.println("CustomerCart User ID: " + customerCart.getUser().getId());
            CustomerCart savedCart = customerCartRepository.createOrder(customerCart);
            return ResponseEntity.ok(savedCart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al agregar el plato al carrito: " + e.getMessage());
        }
    }

}
