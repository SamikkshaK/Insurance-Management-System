package dao;

import entity.Payment;
import java.util.List;

public interface IPaymentDAO {
    boolean addPayment(Payment payment);
    List<Payment> getPaymentsByClientId(int clientId);
}
