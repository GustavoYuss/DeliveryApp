package fei.uv.mx.deliveryapp.Controllers;

import fei.uv.mx.deliveryapp.Models.User;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/deliveryApp/userProfile")
public class UserProfileController {

    @Autowired
    private final UserRepository userRepository;

    public UserProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/showData")
    public String userProfile(@RequestParam("id") int userId,Model model) {
        User user = userRepository.getUser(userId);
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PutMapping("/updateName")
    @ResponseBody
    public ResponseEntity<String> updateName(@RequestBody Map<String, Object> body) {
        int userId = (int) body.get("id");
        String name = (String) body.get("name");
        try {
            User user = userRepository.getUser(userId);
            user.setName(name);
            userRepository.updateUser(user);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Nombre actualizado");
    }

    @PutMapping("/updatePhone")
    @ResponseBody
    public ResponseEntity<String> updatePhone(@RequestBody Map<String, Object> body) {
        int userId = (int) body.get("id");
        String phoneNumber = (String) body.get("phoneNumber");
        try {
            User user = userRepository.getUser(userId);
            user.setPhoneNumber(phoneNumber);
            userRepository.updateUser(user);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Teléfono actualizado");
    }

    @PutMapping("/updateEmail")
    @ResponseBody
    public ResponseEntity<String> updateEmail(@RequestBody Map<String, Object> body) {
        int userId = (int) body.get("id");
        String email = (String) body.get("email");
        try {
            User user = userRepository.getUser(userId);
            user.setEmail(email);
            userRepository.updateUser(user);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Correo electrónico actualizado");
    }

    @PutMapping("/updatePassword")
    @ResponseBody
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, Object> body) {
        int userId = (int) body.get("id");
        String oldPassword = (String) body.get("oldPassword");
        String newPassword = (String) body.get("newPassword");

        try {
            if (userRepository.verifyPassword(userId, oldPassword)) {
                if (userRepository.updatePassword(userId, newPassword)) {
                    return ResponseEntity.ok("Password actualizado");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La contraseña antigua no coincide.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La contraseña antigua no coincide.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
