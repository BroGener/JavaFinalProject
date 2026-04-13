package business.model;

import java.io.Serializable;

public class ActivityCredit implements Serializable {

    private String activityName;
    private double amount;
    private String transactionType;
    private java.sql.Timestamp createdAt;

    public ActivityCredit() {
    }

    public ActivityCredit(String activityName, double amount) {
        this.activityName = activityName;
        this.amount = amount;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
