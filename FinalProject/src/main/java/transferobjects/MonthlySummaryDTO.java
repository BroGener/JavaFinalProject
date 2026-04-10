/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;

/**
 *
 * @author biyababu
 */
public class MonthlySummaryDTO {
   
  private int userId;
    private double totalCredits;
    private double totalDebits;
   

    public MonthlySummaryDTO() {}

    public MonthlySummaryDTO(int userId, double totalCredits, double totalDebits) {
        this.userId = userId;
        this.totalCredits = totalCredits;
        this.totalDebits = totalDebits;
       
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(double totalCredits) {
        this.totalCredits = totalCredits;
    }

    public double getTotalDebits() {
        return totalDebits;
    }

    public void setTotalDebits(double totalDebits) {
        this.totalDebits = totalDebits;
    }

    public double getAmountDue() {
        return totalDebits - totalCredits;
    }
    
}
