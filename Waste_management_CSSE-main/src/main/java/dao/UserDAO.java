package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.IUserDAO;
import model.User;
import utility.DBConnection;

public class UserDAO implements IUserDAO {
    // SQL Queries for User operations
    String SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    String SELECT_ALL_USERS = "SELECT * FROM users";
    String INSERT_USER = "INSERT INTO users (user_type, name, address, phone_number, email, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_USER = "UPDATE users SET user_type=?, name=?, address=?, phone_number=?, email=?, username=?, password=? WHERE user_id = ?";
    String DELETE_USER = "DELETE FROM users WHERE user_id = ?";

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                String userType = rs.getString("user_type");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");

                User user = new User(userID, userType, name, address, phoneNumber, email, username, password);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectUser(int userID) {
        User user = new User();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_ID)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String userType = rs.getString("user_type");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phone_number");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    String password = rs.getString("password");

                    user = new User(userID, userType, name, address, phoneNumber, email, username, password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUser(User user) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_USER)) {

            stmt.setString(1, user.getUserType());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getUsername());
            stmt.setString(7, user.getPassword());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_USER)) {

            stmt.setString(1, user.getUserType());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getUsername());
            stmt.setString(7, user.getPassword());
            stmt.setInt(8, user.getUserID());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userID) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_USER)) {

            stmt.setInt(1, userID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
