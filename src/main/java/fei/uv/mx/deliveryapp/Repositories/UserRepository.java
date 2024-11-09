package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    default User createUser(User user) {
        return save(user);
    }

    default User getUser(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteUser(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateUser(User user) {
        if (existsById(user.getId())) {
            save(user);
            return true;
        }
        return false;
    }

    Optional<User> findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u")
    List<User> getAllUsers();
}
