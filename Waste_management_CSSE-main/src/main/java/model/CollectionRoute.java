package model;

import java.sql.Date;

public class CollectionRoute {
    private int routeId;
    private String routeName;
    private String startLocation;
    private String endLocation;
    private String assignedTruck;
    private Date collectionDate;

    public CollectionRoute() {
        super();
    }

    public CollectionRoute(int routeId, String routeName, String startLocation, String endLocation, String assignedTruck, Date collectionDate) {
        super();
        this.routeId = routeId;
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.assignedTruck = assignedTruck;
        this.collectionDate = collectionDate;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getAssignedTruck() {
        return assignedTruck;
    }

    public void setAssignedTruck(String assignedTruck) {
        this.assignedTruck = assignedTruck;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }
}
