package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    default Payment createPayment(Payment payment) {
        return save(payment);
    }

    default Payment getPayment(int id) {
        return findById(id).orElse(null);
    }

    default boolean deletePayment(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updatePayment(Payment payment) {
        if (existsById(payment.getId())) {
            save(payment);
            return true;
        }
        return false;
    }

    @Query("SELECT p FROM Payment p WHERE p.idUser.id = ?1")
    Payment findByUserId(int userId);
}
