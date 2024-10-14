package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.IPaymentDAO;
import model.Payment;
import utility.DBConnection;

public class PaymentDAO implements IPaymentDAO {
    // SQL Queries for Payment operations
    String SELECT_PAYMENT_BY_ID = "SELECT * FROM payments WHERE payment_id = ?";
    String SELECT_ALL_PAYMENTS = "SELECT * FROM payments";
    String INSERT_PAYMENT = "INSERT INTO payments (user_id, amount, payment_date, payment_method, payment_status) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_PAYMENT = "UPDATE payments SET user_id=?, amount=?, payment_date=?, payment_method=?, payment_status=? WHERE payment_id = ?";
    String DELETE_PAYMENT = "DELETE FROM payments WHERE payment_id = ?";

    @Override
    public List<Payment> selectAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_PAYMENTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                int userId = rs.getInt("user_id");
                float amount = rs.getFloat("amount");
                Date paymentDate = rs.getDate("payment_date");
                String paymentMethod = rs.getString("payment_method");
                String paymentStatus = rs.getString("payment_status");

                Payment payment = new Payment(paymentId, userId, amount, paymentDate, paymentMethod, paymentStatus);
                payments.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public Payment selectPayment(int paymentId) {
        Payment payment = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_PAYMENT_BY_ID)) {

            stmt.setInt(1, paymentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    float amount = rs.getFloat("amount");
                    Date paymentDate = rs.getDate("payment_date");
                    String paymentMethod = rs.getString("payment_method");
                    String paymentStatus = rs.getString("payment_status");

                    payment = new Payment(paymentId, userId, amount, paymentDate, paymentMethod, paymentStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public void insertPayment(Payment payment) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_PAYMENT)) {

            stmt.setInt(1, payment.getUserId());
            stmt.setFloat(2, payment.getAmount());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getPaymentStatus());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_PAYMENT)) {

            stmt.setInt(1, payment.getUserId());
            stmt.setFloat(2, payment.getAmount());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getPaymentStatus());
            stmt.setInt(6, payment.getPaymentId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_PAYMENT)) {

            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
