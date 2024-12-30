package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fei.uv.mx.deliveryapp.Models.Restaurant;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {
    private final RestaurantRepository restaurantRepository;

    public IndexController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/recent")
    public List<Restaurant> getRecentRestaurants() {
        return restaurantRepository.getAllRestaurant();
    }

    @GetMapping("/popular")
    public List<Restaurant> getPopularRestaurants() {
        return restaurantRepository.getAllRestaurant();
    }

    @GetMapping("/cheap")
    public List<Restaurant> getCheapRestaurants() {
        return restaurantRepository.getAllRestaurant();
    }

    @GetMapping("/restaurants")
    public ResponseEntity<?> getRestaurantsByDishType(@RequestParam("category") Integer categoryId) {

        if (categoryId == null) {
            return ResponseEntity.badRequest().body("DishType ID is required");
        }

        try {
            List<Restaurant> restaurants = restaurantRepository.findRestaurantsByDishTypeId(categoryId);
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching restaurants");
        }

    }
}