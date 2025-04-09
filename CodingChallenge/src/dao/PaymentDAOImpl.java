package dao;

import entity.Payment;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class PaymentDAOImpl implements IPaymentDAO {
    private static final Connection connection = DBConnection.getConnection();

    @Override
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payments (payment_date, payment_amount, client_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, payment.getPaymentDate());
            ps.setDouble(2, payment.getPaymentAmount());
            ps.setInt(3, payment.getClientId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in addPayment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Payment> getPaymentsByClientId(int clientId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE client_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("payment_id"),
                        rs.getDate("payment_date"),
                        rs.getDouble("payment_amount"),
                        rs.getInt("client_id")));
            }
        } catch (SQLException e) {
            System.out.println("Error in getPaymentsByClientId: " + e.getMessage());
        }
        return payments;
    }
}
