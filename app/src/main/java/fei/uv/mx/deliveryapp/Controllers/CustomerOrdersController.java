package fei.uv.mx.deliveryapp.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/deliveryApp/customer")
public class CustomerOrdersController {

    @GetMapping("/")
    public String customerOrdersPage(HttpServletRequest request, Model model) {
        return "customerOrders";
    }


}
