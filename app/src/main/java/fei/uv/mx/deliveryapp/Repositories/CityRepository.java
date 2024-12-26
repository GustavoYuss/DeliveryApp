package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    default City createCity(City city) {
        return save(city);
    }

    default City getCity(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteCity(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateCity(City city) {
        if (existsById(city.getId())) {
            save(city);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM City u")
    List<City> getAllCities();
}
