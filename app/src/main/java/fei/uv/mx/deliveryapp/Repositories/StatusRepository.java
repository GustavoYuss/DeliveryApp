package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    default Status createStatus(Status status) {
        return save(status);
    }

    default Status getStatus(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteStatus(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateStatus(Status status) {
        if (existsById(status.getId())) {
            save(status);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM Status u")
    List<Status> getAllStates();
}
