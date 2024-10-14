package interfaces;

import java.util.List;
import model.Payment;

public interface IPaymentDAO {
	// Retrieves all payments
    List<Payment> selectAllPayments();
    
    // Retrieves a specific payments based on ID
    Payment selectPayment(int paymentId);
    
    // Inserts a new payments record
    void insertPayment(Payment payment);
    
    // Updates an existing payments record
    void updatePayment(Payment payment);
    
    // Deletes a payments record
    void deletePayment(int paymentId);
}
