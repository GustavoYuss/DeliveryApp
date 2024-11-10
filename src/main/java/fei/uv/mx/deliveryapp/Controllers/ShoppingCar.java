package fei.uv.mx.deliveryapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCar {
    @GetMapping("/car")
    public String showIndexPage() {
        return "shoppingCar";
    }
}
