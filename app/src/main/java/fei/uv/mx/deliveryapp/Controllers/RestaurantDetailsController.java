package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.Dish;
import fei.uv.mx.deliveryapp.Models.Restaurant;
import fei.uv.mx.deliveryapp.Repositories.DishRepository;
import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantDetailsController {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public RestaurantDetailsController(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @GetMapping("/restaurantDetails")
    public String showRestaurantDetails(@RequestParam("id") int restaurantId, Model model) {
        System.out.println("Si se llego a llamar el meotodo");
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
            return ResponseEntity.noContent().build(); // Retorna 204 si no hay resultados
        }
        return ResponseEntity.ok(dishList);
    }

}
