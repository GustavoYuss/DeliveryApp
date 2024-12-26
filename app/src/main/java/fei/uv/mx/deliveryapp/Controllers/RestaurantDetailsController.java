package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.Restaurant;
import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestaurantDetailsController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDetailsController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/restaurantDetails")
    public String showRestaurantDetails(@RequestParam("id") int restaurantId, Model model) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            model.addAttribute("restaurant", restaurant);
        }
        return "restaurantDetails";
    }

}
