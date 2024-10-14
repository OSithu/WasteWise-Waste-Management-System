package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICollectionRouteDAO;
import model.CollectionRoute;
import utility.DBConnection;

public class CollectionRouteDAO implements ICollectionRouteDAO {
    // SQL Queries for Collection Route operations
    String SELECT_ALL_ROUTES = "SELECT * FROM collection_routes";
    String SELECT_ROUTE_BY_ID = "SELECT * FROM collection_routes WHERE route_id = ?";
    String INSERT_ROUTE = "INSERT INTO collection_routes (route_name, start_location, end_location, assigned_truck, collection_date) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_ROUTE = "UPDATE collection_routes SET route_name=?, start_location=?, end_location=?, assigned_truck=?, collection_date=? WHERE route_id = ?";
    String DELETE_ROUTE = "DELETE FROM collection_routes WHERE route_id = ?";

    @Override
    public List<CollectionRoute> selectAllRoutes() {
        List<CollectionRoute> routes = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_ROUTES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int routeId = rs.getInt("route_id");
                String routeName = rs.getString("route_name");
                String startLocation = rs.getString("start_location");
                String endLocation = rs.getString("end_location");
                String assignedTruck = rs.getString("assigned_truck");
                Date collectionDate = rs.getDate("collection_date");

                CollectionRoute route = new CollectionRoute(routeId, routeName, startLocation, endLocation, assignedTruck, collectionDate);
                routes.add(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }

    @Override
    public CollectionRoute selectRoute(int routeId) {
        CollectionRoute route = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ROUTE_BY_ID)) {

            stmt.setInt(1, routeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String routeName = rs.getString("route_name");
                    String startLocation = rs.getString("start_location");
                    String endLocation = rs.getString("end_location");
                    String assignedTruck = rs.getString("assigned_truck");
                    Date collectionDate = rs.getDate("collection_date");

                    route = new CollectionRoute(routeId, routeName, startLocation, endLocation, assignedTruck, collectionDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    public void insertRoute(CollectionRoute route) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ROUTE)) {

            stmt.setString(1, route.getRouteName());
            stmt.setString(2, route.getStartLocation());
            stmt.setString(3, route.getEndLocation());
            stmt.setString(4, route.getAssignedTruck());
            stmt.setDate(5, route.getCollectionDate());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoute(CollectionRoute route) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_ROUTE)) {

            stmt.setString(1, route.getRouteName());
            stmt.setString(2, route.getStartLocation());
            stmt.setString(3, route.getEndLocation());
            stmt.setString(4, route.getAssignedTruck());
            stmt.setDate(5, route.getCollectionDate());
            stmt.setInt(6, route.getRouteId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoute(int routeId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_ROUTE)) {

            stmt.setInt(1, routeId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
