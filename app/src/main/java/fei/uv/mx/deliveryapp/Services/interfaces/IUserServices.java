package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.User;
import java.util.List;
import java.util.Optional;

public interface IUserServices {
    User createUser(User user);
    User getUser(int id);
    boolean deleteUser(int id);
    boolean updateUser(User user);
    List<User> getAllUsers();
    Optional<User> authenticateUser(String email, String password);
    int getUserByEmail(String email);
}
