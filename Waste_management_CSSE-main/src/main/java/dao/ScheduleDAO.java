package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.IScheduleDAO;
import model.Schedule;
import utility.DBConnection;

public class ScheduleDAO implements IScheduleDAO {
    // SQL Queries for Schedule operations
    String SELECT_SCHEDULE_BY_ID = "SELECT * FROM schedules WHERE schedule_id = ?";
    String SELECT_ALL_SCHEDULES = "SELECT * FROM schedules";
    String INSERT_SCHEDULE = "INSERT INTO schedules (user_id, pickup_date, waste_type, collection_area) VALUES (?, ?, ?, ?)";
    String UPDATE_SCHEDULE = "UPDATE schedules SET user_id=?, pickup_date=?, waste_type=?, collection_area=? WHERE schedule_id = ?";
    String DELETE_SCHEDULE = "DELETE FROM schedules WHERE schedule_id = ?";

    @Override
    public List<Schedule> selectAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_SCHEDULES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int scheduleId = rs.getInt("schedule_id");
                int userId = rs.getInt("user_id");
                Date pickupDate = rs.getDate("pickup_date");
                String wasteType = rs.getString("waste_type");
                String collectionArea = rs.getString("collection_area");

                Schedule schedule = new Schedule(scheduleId, userId, pickupDate, wasteType, collectionArea);
                schedules.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public Schedule selectSchedule(int scheduleId) {
        Schedule schedule = new Schedule();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_SCHEDULE_BY_ID)) {

            stmt.setInt(1, scheduleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    Date pickupDate = rs.getDate("pickup_date");
                    String wasteType = rs.getString("waste_type");
                    String collectionArea = rs.getString("collection_area");

                    schedule = new Schedule(scheduleId, userId, pickupDate, wasteType, collectionArea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
    }

    @Override
    public void insertSchedule(Schedule schedule) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_SCHEDULE)) {

            stmt.setInt(1, schedule.getUserId());
            stmt.setDate(2, schedule.getPickupDate());
            stmt.setString(3, schedule.getWasteType());
            stmt.setString(4, schedule.getCollectionArea());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_SCHEDULE)) {

            stmt.setInt(1, schedule.getUserId());
            stmt.setDate(2, schedule.getPickupDate());
            stmt.setString(3, schedule.getWasteType());
            stmt.setString(4, schedule.getCollectionArea());
            stmt.setInt(5, schedule.getScheduleId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSchedule(int scheduleId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_SCHEDULE)) {

            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
