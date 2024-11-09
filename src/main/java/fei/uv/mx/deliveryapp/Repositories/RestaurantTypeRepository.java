package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTypeRepository extends JpaRepository<User, Integer> {
}
