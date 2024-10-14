package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.IFeedbackDAO;
import model.Feedback;
import utility.DBConnection;

public class FeedbackDAO implements IFeedbackDAO {
    // SQL Queries for Feedback operations
    String SELECT_FEEDBACK_BY_ID = "SELECT * FROM feedback WHERE feedback_id = ?";
    String SELECT_ALL_FEEDBACKS = "SELECT * FROM feedback";
    String INSERT_FEEDBACK = "INSERT INTO feedback (user_id, feedback_type, message, response_status) VALUES (?, ?, ?, ?)";
    String UPDATE_FEEDBACK = "UPDATE feedback SET user_id=?, feedback_type=?, message=?, response_status=? WHERE feedback_id = ?";
    String DELETE_FEEDBACK = "DELETE FROM feedback WHERE feedback_id = ?";

    @Override
    public List<Feedback> selectAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_FEEDBACKS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                int userId = rs.getInt("user_id");
                String feedbackType = rs.getString("feedback_type");
                String message = rs.getString("message");
                String responseStatus = rs.getString("response_status");

                Feedback feedback = new Feedback(feedbackId, userId, feedbackType, message, responseStatus);
                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    @Override
    public Feedback selectFeedback(int feedbackId) {
        Feedback feedback = new Feedback();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_FEEDBACK_BY_ID)) {

            stmt.setInt(1, feedbackId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String feedbackType = rs.getString("feedback_type");
                    String message = rs.getString("message");
                    String responseStatus = rs.getString("response_status");

                    feedback = new Feedback(feedbackId, userId, feedbackType, message, responseStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedback;
    }

    @Override
    public void insertFeedback(Feedback feedback) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_FEEDBACK)) {

            stmt.setInt(1, feedback.getUserId());
            stmt.setString(2, feedback.getFeedbackType());
            stmt.setString(3, feedback.getMessage());
            stmt.setString(4, feedback.getResponseStatus());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_FEEDBACK)) {

            stmt.setInt(1, feedback.getUserId());
            stmt.setString(2, feedback.getFeedbackType());
            stmt.setString(3, feedback.getMessage());
            stmt.setString(4, feedback.getResponseStatus());
            stmt.setInt(5, feedback.getFeedbackId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFeedback(int feedbackId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_FEEDBACK)) {

            stmt.setInt(1, feedbackId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
