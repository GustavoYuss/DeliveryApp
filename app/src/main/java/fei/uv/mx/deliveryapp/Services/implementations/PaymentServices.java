package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.Payment;
import fei.uv.mx.deliveryapp.Repositories.PaymentRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IPaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServices implements IPaymentServices {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.createPayment(payment);
    }

    @Override
    public Payment getPayment(int id) {
        return paymentRepository.getPayment(id);
    }

    @Override
    public boolean deletePayment(int id) {
        return paymentRepository.deletePayment(id);
    }

    @Override
    public boolean updatePayment(Payment payment) {
        return paymentRepository.updatePayment(payment);
    }

    @Override
    public List<Payment> getPaymentsByUserId(int userId) {
        return paymentRepository.findByUserId(userId);
    }
}
