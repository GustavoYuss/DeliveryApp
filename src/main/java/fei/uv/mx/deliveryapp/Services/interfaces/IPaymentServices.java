package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.Payment;
import java.util.List;

public interface IPaymentServices {
    Payment createPayment(Payment payment);
    Payment getPayment(int id);
    boolean deletePayment(int id);
    boolean updatePayment(Payment payment);
    List<Payment> getPaymentsByUserId(int userId);
}
