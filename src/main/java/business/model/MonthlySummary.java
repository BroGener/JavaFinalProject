package business.model;

import java.io.Serializable;

public class MonthlySummary implements Serializable {
    private int userId;
    private int year;
    private int month;
    private int tripCount;
    private double totalDistanceKm;
    private double totalAmount;

    public MonthlySummary() {}

    public MonthlySummary(int userId, int year, int month, int tripCount, double totalDistanceKm, double totalAmount) {
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.tripCount = tripCount;
        this.totalDistanceKm = totalDistanceKm;
        this.totalAmount = totalAmount;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public int getTripCount() { return tripCount; }
    public void setTripCount(int tripCount) { this.tripCount = tripCount; }
    public double getTotalDistanceKm() { return totalDistanceKm; }
    public void setTotalDistanceKm(double totalDistanceKm) { this.totalDistanceKm = totalDistanceKm; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
