package fei.uv.mx.deliveryapp.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCar {

    @GetMapping("/car")
    public String carPage(HttpServletRequest request, Model model) {
        return "shoppingCar";
    }
}
