package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.DTOs.OrderDTO;
import fei.uv.mx.deliveryapp.DTOs.OrderDishDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ShoppingCarController {

    @GetMapping("/car")
    public String carPage(HttpServletRequest request, Model model) {
        OrderDTO orderDTO = new OrderDTO();
        ArrayList<OrderDishDTO> dishes = new ArrayList<OrderDishDTO>();
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("dishes", dishes);
        return "shoppingCar";
    }
}
