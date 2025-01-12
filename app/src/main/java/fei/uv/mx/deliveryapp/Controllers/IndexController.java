package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fei.uv.mx.deliveryapp.Models.Restaurant;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Restaurant>> getRecentRestaurants() {
        try {
            return ResponseEntity.ok(restaurantRepository.getAllRestaurant());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Restaurant>> getPopularRestaurants() {
        try {
            return ResponseEntity.ok(restaurantRepository.getAllRestaurant());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cheap")
    public ResponseEntity<List<Restaurant>> getCheapRestaurants() {
        try {
            return ResponseEntity.ok(restaurantRepository.getAllRestaurant());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam("query") String query) {
        try {
            List<Restaurant> results = restaurantRepository.findByNameRestaurantContainingIgnoreCase(query);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}