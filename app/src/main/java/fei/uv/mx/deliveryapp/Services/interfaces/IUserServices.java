package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.User;
import java.util.List;
import java.util.Optional;

public interface IUserServices {
    public User createUser(User user);
    public User getUser(int id);
    public boolean deleteUser(int id);
    public boolean updateUser(User user);
    public List<User> getAllUsers();
    public Optional<User> authenticateUser(String email, String password);
}
