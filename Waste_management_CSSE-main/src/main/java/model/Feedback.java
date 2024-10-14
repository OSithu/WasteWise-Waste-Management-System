package model;

public class Feedback {
    private int feedbackId;
    private int userId;
    private String feedbackType;
    private String message;
    private String responseStatus;

    public Feedback() {
        super();
    }

    public Feedback(int feedbackId, int userId, String feedbackType, String message, String responseStatus) {
        super();
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.feedbackType = feedbackType;
        this.message = message;
        this.responseStatus = responseStatus;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
