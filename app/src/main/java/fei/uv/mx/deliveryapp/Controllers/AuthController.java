package fei.uv.mx.deliveryapp.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fei.uv.mx.deliveryapp.Models.User;
import fei.uv.mx.deliveryapp.Models.VerificationToken;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import fei.uv.mx.deliveryapp.Repositories.VerificationTokenRepository;
import fei.uv.mx.deliveryapp.Security.AuthService;
import fei.uv.mx.deliveryapp.Services.implementations.EmailServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailServices emailService;

    @GetMapping("/login")
    public String index(Model model) {
        model.addAttribute("UserNew", new User());
        return "login";
    }

    @PostMapping("/startSession")
    public String startSession(@ModelAttribute("UserNew") User user, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = authService.authenticate(user.getEmail(), user.getPassword(), request);
            Optional<User> optionalUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

            if (optionalUser.isPresent()) {
                User foundUser = optionalUser.get();

                Cookie jwtCookie = new Cookie("jwtToken", token);
                jwtCookie.setHttpOnly(true);
                jwtCookie.setSecure(false);
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge((int) authService.getJwtExpirationMs() / 1000);
                response.addCookie(jwtCookie);

                Map<String, Object> simpleUser = new HashMap<>();
                simpleUser.put("id", foundUser.getId());
                simpleUser.put("name", foundUser.getName());
                simpleUser.put("email", foundUser.getEmail());
                simpleUser.put("phoneNumber", foundUser.getPhoneNumber());

                model.addAttribute("authenticatedUser", simpleUser);
                return "index";

            } else {
                throw new RuntimeException("Usuario no encontrado");
            }

        } catch (RuntimeException e) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("UserNew", new User());
            return "login";
        }
    }


    @PostMapping("/registerUser")
    public String registerNewUser(Model model, User user, HttpServletRequest request) {
        try {

            user.setEnabled(false);
            userRepository.createUser(user);

            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken(user, token);
            verificationTokenRepository.createVerificationToken(verificationToken);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            emailService.sendVerificationEmail(user, token, appUrl);
            model.addAttribute("success", "Usuario registrado exitosamente. Por favor, verifica tu correo electrónico.");

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al registrar el usuario.");
        }
        model.addAttribute("UserNew", new User());
        return "login";
    }

    @GetMapping("/confirm")
    public String confirmUser(@RequestParam("token") String token, Model model) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));

        if (verificationToken == null || verificationToken.getExpiryDate().isBefore(now)) {
            System.out.println("Token encontrado: " + verificationToken.getToken());
            System.out.println("Fecha de expiración del token: " + verificationToken.getExpiryDate());
            System.out.println("Fecha actual: " + LocalDateTime.now());

            model.addAttribute("error", "El token no es válido o ha expirado.");
            return "error";
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        System.out.println("Nombre: " + user.getName() + "Estado: "  + user.isEnabled());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
        }
        User updatedUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User no encontrado"));
        System.out.println("Estado actualizado: " + updatedUser.isEnabled());

        model.addAttribute("success", "Tu cuenta ha sido confirmada. Ahora puedes iniciar sesión.");
        return "login";
    }

    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("jwtToken", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(false);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
        return "login";
    }

    @GetMapping("/protectedRoute")
    public String protectedRoute(HttpServletRequest request, Model model) {
        String token = authService.getJwtFromCookies(request);

        if (token != null && authService.validateToken(token)) {
            model.addAttribute("message", "Acceso autorizado a la ruta protegida.");
            return "protectedPage";
        } else {
            return "login";
        }
    }
}