package model;

import java.sql.Timestamp;
import java.sql.Date;

public class SpecialRequest {

    private int requestId;
    private int userId;
    private Timestamp requestDate;
    private String wasteType;
    private Date requestedPickupDate;
    private String additionalDetails;
    private String status;

    public SpecialRequest() {
        super();
    }

    public SpecialRequest(int requestId, int userId, Timestamp requestDate, String wasteType, Date requestedPickupDate, String additionalDetails, String status) {
        super();
        this.requestId = requestId;
        this.userId = userId;
        this.requestDate = requestDate;
        this.wasteType = wasteType;
        this.requestedPickupDate = requestedPickupDate;
        this.additionalDetails = additionalDetails;
        this.status = status;
    }

    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public Date getRequestedPickupDate() {
        return requestedPickupDate;
    }

    public void setRequestedPickupDate(Date requestedPickupDate) {
        this.requestedPickupDate = requestedPickupDate;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
