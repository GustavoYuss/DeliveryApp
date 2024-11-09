package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.User;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String index(Model model) {
        model.addAttribute("UserNew", new User());
        return "login";
    }

    @PostMapping("/startSession")
    public String startSession(@ModelAttribute("UserNew") User user, Model model) {
        Optional<User> authenticatedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (authenticatedUser.isPresent()) {
            model.addAttribute("user", authenticatedUser.get());
            return "index";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("UserNew", new User());
            return "login";
        }
    }

    @PostMapping("/registerUser")
    public String registerNewUser(Model model, User user) {
        try {
            userRepository.createUser(user);
            model.addAttribute("success", "Usuario registrado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al registrar el usuario.");
        }
        model.addAttribute("UserNew", new User());
        return "login";
    }
}