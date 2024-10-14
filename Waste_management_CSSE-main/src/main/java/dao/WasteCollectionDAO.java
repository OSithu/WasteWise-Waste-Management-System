package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import interfaces.IWasteCollectionDAO;
import model.WasteCollection;
import utility.DBConnection;

public class WasteCollectionDAO implements IWasteCollectionDAO {
    // SQL Queries for WasteCollection operations
    String SELECT_COLLECTION_BY_ID = "SELECT * FROM waste_collection WHERE collection_id = ?";
    
    String SELECT_ALL_COLLECTIONS = "SELECT * FROM waste_collection";
    
    String INSERT_COLLECTION = "INSERT INTO waste_collection (user_id, collection_date, waste_type, weight) VALUES (?, ?, ?, ?)";
    
    String UPDATE_COLLECTION = "UPDATE waste_collection SET user_id=?, collection_date=?, waste_type=?, weight=? WHERE collection_id = ?";
    
    String DELETE_COLLECTION = "DELETE FROM waste_collection WHERE collection_id = ?";

    @Override
    public List<WasteCollection> selectAllCollections() {
        List<WasteCollection> collections = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_COLLECTIONS);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int collectionId = rs.getInt("collection_id");
                int userId = rs.getInt("user_id");
                java.sql.Date collectionDate = rs.getDate("collection_date");
                String wasteType = rs.getString("waste_type");
                float weight = rs.getFloat("weight");
                
                WasteCollection collection = new WasteCollection(collectionId, userId, collectionDate, wasteType, weight);
                collections.add(collection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collections;
    }

    @Override
    public WasteCollection selectCollection(int collectionId) {
        WasteCollection collection = new WasteCollection();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_COLLECTION_BY_ID)) {
            
            stmt.setInt(1, collectionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    java.sql.Date collectionDate = rs.getDate("collection_date");
                    String wasteType = rs.getString("waste_type");
                    float weight = rs.getFloat("weight");
                    
                    collection = new WasteCollection(collectionId, userId, collectionDate, wasteType, weight);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }

    @Override
    public void insertCollection(WasteCollection collection) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_COLLECTION)) {
            
            stmt.setInt(1, collection.getUserId());
            stmt.setDate(2, new java.sql.Date(collection.getCollectionDate().getTime()));
            stmt.setString(3, collection.getWasteType());
            stmt.setFloat(4, collection.getWeight());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCollection(WasteCollection collection) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_COLLECTION)) {
            
            stmt.setInt(1, collection.getUserId());
            stmt.setDate(2, new java.sql.Date(collection.getCollectionDate().getTime()));
            stmt.setString(3, collection.getWasteType());
            stmt.setFloat(4, collection.getWeight());
            stmt.setInt(5, collection.getCollectionId());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCollection(int collectionId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_COLLECTION)) {
            
            stmt.setInt(1, collectionId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
