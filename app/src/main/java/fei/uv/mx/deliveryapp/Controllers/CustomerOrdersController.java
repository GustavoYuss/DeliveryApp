package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.DishOrderDTO;
import fei.uv.mx.deliveryapp.Models.*;
import fei.uv.mx.deliveryapp.Repositories.DishRepository;
import fei.uv.mx.deliveryapp.Repositories.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/deliveryApp/customer")
public class CustomerOrdersController {

    private List<Order> orders;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;


    @GetMapping("/")
    public String customerOrdersPage(HttpServletRequest request, Model model) {
        orders = orderRepository.findByDateBetween(LocalDate.now(), LocalDate.now());
        model.addAttribute("orders", orders);
        model.addAttribute("startDate", LocalDate.now());
        model.addAttribute("endDate", LocalDate.now());

        return "customerOrders";
    }

    @GetMapping("/filter")
    public String filterOrders(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        if (startDate != null && endDate != null) {
            List<Order> orders = orderRepository.findByDateBetween(startDate, endDate);
            model.addAttribute("orders", orders);
        }

        model.addAttribute("startDate", startDate != null ? startDate : LocalDate.now());
        model.addAttribute("endDate", endDate != null ? endDate : LocalDate.now());

        return "customerOrders";
    }

    @GetMapping("/last-update")
    public ResponseEntity<String> getLastUpdate() {
        String lastUpdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd 'de' MMM. 'de' yyyy HH:mm"));
        return ResponseEntity.ok(lastUpdate);
    }

    @GetMapping("/getDishesFromOrder")
    public ResponseEntity<List<DishOrderDTO>> getDishesFromOrder(HttpServletRequest request) {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        List<DishOrderDTO> dishes = dishRepository.findDishOrderDTOsByOrderId(idOrder);
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/updateStatusOrder")
    public ResponseEntity<String> updateStatusOrder(HttpServletRequest request) {
        System.out.println("FEOS TODOS ALV");
        int idStatus = 4;
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        Status status = new Status();
        status.setId(idStatus);
        orderRepository.updateOrderRestaurantStatusByOrderId(idStatus, idOrder);
        orderRepository.updateOrderStatus(idStatus, idOrder);
        /*
        Order order = orderRepository.getOrder(idOrder);
        order.setStatus(status);
        orderRepository.updateOrder(order);*/
        return ResponseEntity.ok("restaurantManagement");
    }
}
