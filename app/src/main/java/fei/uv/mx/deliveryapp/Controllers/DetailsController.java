package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Services.implementations.DishServices;
import fei.uv.mx.deliveryapp.Services.implementations.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fei.uv.mx.deliveryapp.Models.Dish;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailsController {
    @Autowired
    private DishServices dishServices;

    @Autowired
    private ReviewServices reviewServices;

    @GetMapping("/Details")
    public String showIndexPage(@RequestParam(name = "product", required = false) int product, Model model) {
        Dish dish = dishServices.getDish(product);
        model.addAttribute("product", dish);
        model.addAttribute("dishes", dishServices.getTop5DishesByRestaurantId(dish.getRestaurant().getId(), dish.getId()));
        model.addAttribute("review", reviewServices.getReviewRating(product));
        return "details";
    }
}
