package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.DTOs.ReviewDTO;
import fei.uv.mx.deliveryapp.Models.*;
import fei.uv.mx.deliveryapp.Repositories.*;
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
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CustomerCarRepository customerCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

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
        List<Dish> dishList = dishRepository.findByRestaurantId(restaurantId);
        if (dishList == null || dishList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dishList);
    }

    @PostMapping("/addDishToCar")
    public ResponseEntity<?> addDishToCart(@RequestBody CustomerCart customerCart) {
        try {
            //User user = userRepository.findById(1).orElseThrow(() -> new Exception("Usuario no encontrado"));
            //customerCart.setUser(user);
            customerCartRepository.createOrder(customerCart);
            return ResponseEntity.ok("Registro correcto");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getReviews")
    public ResponseEntity<?> getReviews(@RequestParam("id") int restaurantId) {
        List<Review> reviews;
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        try {
            reviews = reviewRepository.getReviewsByRestaurantId(restaurantId);
            reviews.forEach(review -> {
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setDescription(review.getDescription());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setUserName(review.getUser().getName());
                reviewDTOS.add(reviewDTO);
            });
            return ResponseEntity.ok(reviewDTOS);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/registerReviews")
    public ResponseEntity<Review> registerReviews(@RequestBody Review review) {
        try {
            System.out.println(review.getDescription());
            System.out.println(review.getRating());
            System.out.println(review.getUser().getId());
            System.out.println(review.getRestaurant().getId());
            return ResponseEntity.ok(reviewRepository.createReview(review));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
