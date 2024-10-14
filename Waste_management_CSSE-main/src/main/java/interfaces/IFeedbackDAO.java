package interfaces;

import java.util.List;
import model.Feedback;

public interface IFeedbackDAO {
	// Retrieves all feedbacks
    List<Feedback> selectAllFeedbacks();
    
    // Retrieves a specific feedback based on ID
    Feedback selectFeedback(int feedbackId); 
 
    // Inserts a new feedback record
    void insertFeedback(Feedback feedback); 
    
    // Updates an existing feedback record
    void updateFeedback(Feedback feedback); 
    
    
     void deleteFeedback(int feedbackId); 
}
