package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTypeRepository  extends JpaRepository<UserType, Integer> {
    default UserType createUserType(UserType userType) {
        return save(userType);
    }

    default UserType getUserType(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteUserType(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateUserType(UserType userType) {
        if (existsById(userType.getId())) {
            save(userType);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM UserType u")
    List<UserType> getAllUserTypes();
}
