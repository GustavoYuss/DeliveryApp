package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.CustomerCart;
import fei.uv.mx.deliveryapp.Models.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerCarRepository extends JpaRepository<CustomerCart, Integer> {

    default CustomerCart createOrder(CustomerCart customerCart) {
        Optional<CustomerCart> existingCart = findByUserIdAndDishId(
                customerCart.getUser().getId(),
                customerCart.getDish().getId()
        );

        if (existingCart.isPresent()) {
            CustomerCart cart = existingCart.get();
            int newQuantity = cart.getQuantity() + customerCart.getQuantity();
            cart.setQuantity(newQuantity);
            cart.setCostByProduct(cart.getCostByProduct() + customerCart.getCostByProduct());
            return save(cart);
        }

        return save(customerCart);
    }


    default boolean deleteCustomerCart(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateCustomerCart(CustomerCart customerCart) {
        if (existsById(customerCart.getId())) {
            save(customerCart);
            return true;
        }
        return false;
    }

    default CustomerCart getCustomerCartsById(int id) {
        try {
            return findById(id).orElse(null);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al obtener el customerCart con ID: " + id, e);
        }
    }

    @Query("SELECT o FROM CustomerCart o WHERE o.user.id = ?1")
    List<CustomerCart> findByCustomerId(int customerId);

    @Query("SELECT COUNT(c) FROM CustomerCart c WHERE c.user.id = ?1")
    int countByCustomer(int customerId);

    Optional<CustomerCart> findByUserIdAndDishId(Integer userId, Integer dishId);

}
