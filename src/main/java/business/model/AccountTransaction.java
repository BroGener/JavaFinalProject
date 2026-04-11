package business.model;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountTransaction implements Serializable {
    private Integer transactionId;
    private Integer userId;
    private String activityName;
    private double amount;
    private String transactionType;
    private LocalDate transactionDate;

    public AccountTransaction() {}

    public AccountTransaction(Integer transactionId, Integer userId, String activityName, double amount, String transactionType, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.activityName = activityName;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() { return transactionId; }
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
}
