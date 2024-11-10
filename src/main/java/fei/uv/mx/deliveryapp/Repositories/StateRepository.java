package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {
    default State createState(State state) {
        return save(state);
    }

    default State getState(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteState(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateState(State state) {
        if (existsById(state.getId())) {
            save(state);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM State u")
    List<State> getAllStates();
}
