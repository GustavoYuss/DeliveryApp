package fei.uv.mx.deliveryapp.Repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import fei.uv.mx.deliveryapp.Models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    default User createUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        try {
            return save(user);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage(), e);
        }
    }

    default User getUser(int id) {
        try {
            return findById(id).orElse(null);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al obtener el usuario con ID: " + id, e);
        }
    }

    default boolean deleteUser(int id) {
        try {
            if (existsById(id)) {
                deleteById(id);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al eliminar el usuario con ID: " + id, e);
        }
    }

    default boolean updateUser(User user) {
        try {
            if (existsById(user.getId())) {
                save(user);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }

    default Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> user = findUserByEmail(email);
        if (user.isPresent()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User userFound = user.get();
            if (passwordEncoder.matches(password, userFound.getPassword())) {
                return Optional.of(userFound);
            }
        }
        return Optional.empty();
    }


    default boolean verifyPassword(int userID, String password) {
        User user = getUser(userID);
        if(user != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(password, user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    default boolean updatePassword(int userID, String newPassword) {
        try {
            User user = getUser(userID);
            if (user != null) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(hashedPassword);
                save(user);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }

    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u")
    List<User> getAllUsers();

    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    int findUserIdByEmail(String email);
}

