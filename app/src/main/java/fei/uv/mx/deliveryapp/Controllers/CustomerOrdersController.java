package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.Order;
import fei.uv.mx.deliveryapp.Repositories.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
