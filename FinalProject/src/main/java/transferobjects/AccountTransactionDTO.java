/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;
import java.sql.Timestamp;
/**
 *
 * @author biyababu
 */
public class AccountTransactionDTO {
  private int transactionId;
    private int userId;
    private String transactionType; 
    private String sourceType;     
    private double amount;
    private String description;
    private Timestamp createdAt;  
    
    public AccountTransactionDTO() {
    }
     public AccountTransactionDTO(int transactionId, int userId, String transactionType, String sourceType, 
                                  double amount, String description, Timestamp createdAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.transactionType = transactionType;
        this.sourceType = sourceType;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }
   public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    }
    
    
    
    
    
    
    
    
   
    

