package model;

import java.sql.Date;

public class Schedule {

    private int scheduleId;
    private int userId;
    private Date pickupDate;
    private String wasteType;
    private String collectionArea;

    public Schedule() {
        super();
    }

    public Schedule(int scheduleId, int userId, Date pickupDate, String wasteType, String collectionArea) {
        super();
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.pickupDate = pickupDate;
        this.wasteType = wasteType;
        this.collectionArea = collectionArea;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public String getCollectionArea() {
        return collectionArea;
    }

    public void setCollectionArea(String collectionArea) {
        this.collectionArea = collectionArea;
    }
}
