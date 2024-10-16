package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import interfaces.ISpecialRequestDAO;
import model.SpecialRequest;
import utility.DBConnection;

public class SpecialRequestDAO implements ISpecialRequestDAO {
    // SQL Queries for Special Request operations
    String SELECT_REQUEST_BY_ID = "SELECT * FROM special_requests WHERE request_id = ?";
    String SELECT_ALL_REQUESTS = "SELECT * FROM special_requests";
    String INSERT_REQUEST = "INSERT INTO special_requests (user_id, request_date, waste_type, requested_pickup_date, additional_details, status) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_REQUEST = "UPDATE special_requests SET user_id=?, request_date=?, waste_type=?, requested_pickup_date=?, additional_details=?, status=? WHERE request_id = ?";
    String DELETE_REQUEST = "DELETE FROM special_requests WHERE request_id = ?";

    @Override
    public List<SpecialRequest> selectAllRequests() {
        List<SpecialRequest> requests = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_REQUESTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                int userId = rs.getInt("user_id");
                Timestamp requestDate = rs.getTimestamp("request_date");
                String wasteType = rs.getString("waste_type");
                Date requestedPickupDate = rs.getDate("requested_pickup_date");
                String additionalDetails = rs.getString("additional_details");
                String status = rs.getString("status");

                SpecialRequest request = new SpecialRequest(requestId, userId, requestDate, wasteType, requestedPickupDate, additionalDetails, status);
                requests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public SpecialRequest selectRequest(int requestId) {
        SpecialRequest request = new SpecialRequest();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_REQUEST_BY_ID)) {

            stmt.setInt(1, requestId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    Timestamp requestDate = rs.getTimestamp("request_date");
                    String wasteType = rs.getString("waste_type");
                    Date requestedPickupDate = rs.getDate("requested_pickup_date");
                    String additionalDetails = rs.getString("additional_details");
                    String status = rs.getString("status");

                    request = new SpecialRequest(requestId, userId, requestDate, wasteType, requestedPickupDate, additionalDetails, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public void insertRequest(SpecialRequest request) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_REQUEST)) {

            stmt.setInt(1, request.getUserId());
            stmt.setTimestamp(2, request.getRequestDate());
            stmt.setString(3, request.getWasteType());
            stmt.setDate(4, request.getRequestedPickupDate());
            stmt.setString(5, request.getAdditionalDetails());
            stmt.setString(6, request.getStatus());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRequest(SpecialRequest request) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_REQUEST)) {

            stmt.setInt(1, request.getUserId());
            stmt.setTimestamp(2, request.getRequestDate());
            stmt.setString(3, request.getWasteType());
            stmt.setDate(4, request.getRequestedPickupDate());
            stmt.setString(5, request.getAdditionalDetails());
            stmt.setString(6, request.getStatus());
            stmt.setInt(7, request.getRequestId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRequest(int requestId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_REQUEST)) {

        	
        	
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
