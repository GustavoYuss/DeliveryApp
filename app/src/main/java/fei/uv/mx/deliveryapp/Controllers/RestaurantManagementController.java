package fei.uv.mx.deliveryapp.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantManagementController {

    @GetMapping("/restaurantManagement")
    public String restaurantManagementPage(HttpServletRequest request, Model model) {
        return "restaurantManagement";
    }

}
