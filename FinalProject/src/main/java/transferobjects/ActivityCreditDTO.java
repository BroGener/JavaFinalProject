/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;

/**
 *
 * @author biyababu
 */
public class ActivityCreditDTO {
  private int userId;
    private String email;
    private String role;
    private String activityType;
    private int activityCount;
    private double totalCredit;

    public ActivityCreditDTO() {}

    public ActivityCreditDTO(int userId, String email, String role, String activityType, int activityCount, double totalCredit) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.activityType = activityType;
        this.activityCount = activityCount;
        this.totalCredit = totalCredit;
    }

    public int getUserId() { 
        return userId; 
    }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }

    public String getEmail() { 
        return email;
    }
    public void setEmail(String email) {
        this.email = email; 
    }

    public String getRole() { 
        return role; 
    }
    public void setRole(String role) { 
        this.role = role; 
    }

    public String getActivityType() { 
        return activityType; 
    }
    public void setActivityType(String activityType) {
        this.activityType = activityType; 
    }

    public int getActivityCount() { 
        return activityCount; 
    }
    public void setActivityCount(int activityCount) { 
        this.activityCount = activityCount; 
    }

    public double getTotalCredit() { 
        return totalCredit; 
    }
    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit; 
    }
  
}
