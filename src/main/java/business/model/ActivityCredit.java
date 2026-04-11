package business.model;

import java.io.Serializable;

public class ActivityCredit implements Serializable {
    private String activityName;
    private double amount;

    public ActivityCredit() {}

    public ActivityCredit(String activityName, double amount) {
        this.activityName = activityName;
        this.amount = amount;
    }

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
