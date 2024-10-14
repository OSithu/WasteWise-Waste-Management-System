package model;

import java.util.Date;

public class WasteCollection {
    private int collectionId;
    private int userId;
    private Date collectionDate;
    private String wasteType;
    private float weight;

    public WasteCollection() {
        super();
    }

    public WasteCollection(int collectionId, int userId, Date collectionDate, String wasteType, float weight) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.collectionDate = collectionDate;
        this.wasteType = wasteType;
        this.weight = weight;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
