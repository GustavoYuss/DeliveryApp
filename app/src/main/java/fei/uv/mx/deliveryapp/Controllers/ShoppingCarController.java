package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.CustomerCart;
import fei.uv.mx.deliveryapp.Repositories.CustomerCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deliveryApp/shoppingCar")
public class ShoppingCarController {

    @Autowired
    private final CustomerCarRepository customerCarRepository;

    public ShoppingCarController(CustomerCarRepository customerCarRepository) {
        this.customerCarRepository = customerCarRepository;
    }

    @GetMapping("/")
    public String index() {
        return "shoppingCar";
    }

    


}
