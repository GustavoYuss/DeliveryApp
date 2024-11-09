package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.User;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserServices {
    @Autowired
    UserRepository userRepository;
    /**
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUser(int id) {
        return userRepository.getUser(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    /**
     * @return
     */
    public Optional<User> authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
