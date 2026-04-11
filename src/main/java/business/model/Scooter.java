package business.model;

import java.io.Serializable;

public class Scooter implements Serializable {
    private Integer scooterId;
    private Integer sponsorUserId;
    private String vehicleNumber;
    private String make;
    private String model;
    private String color;
    private int batteryCapacity;
    private int currentChargeLevel;
    private String status;
    private Integer currentStationId;

    public Scooter() {
    }

    public Scooter(Integer scooterId, Integer sponsorUserId, String vehicleNumber, String make,
            String model, String color, int batteryCapacity, int currentChargeLevel,
            String status, Integer currentStationId) {
        this.scooterId = scooterId;
        this.sponsorUserId = sponsorUserId;
        this.vehicleNumber = vehicleNumber;
        this.make = make;
        this.model = model;
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.currentChargeLevel = currentChargeLevel;
        this.status = status;
        this.currentStationId = currentStationId;
    }

    public Integer getScooterId() { return scooterId; }
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }
    public Integer getSponsorUserId() { return sponsorUserId; }
    public void setSponsorUserId(Integer sponsorUserId) { this.sponsorUserId = sponsorUserId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    public int getCurrentChargeLevel() { return currentChargeLevel; }
    public void setCurrentChargeLevel(int currentChargeLevel) { this.currentChargeLevel = currentChargeLevel; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getCurrentStationId() { return currentStationId; }
    public void setCurrentStationId(Integer currentStationId) { this.currentStationId = currentStationId; }
}
