package business.builder;

import business.model.Scooter;

public class ScooterBuilder implements Builder<Scooter> {
    private String vehicleNumber;
    private String make;
    private String model;
    private String color;//we don't need that
    private int batteryCapacity;
    private int currentChargeLevel;//we don't need that
    private String status;
    private Integer sponsorUserId;
    private Integer currentStationId;//we don't need that

    public ScooterBuilder setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public ScooterBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    public ScooterBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ScooterBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public ScooterBuilder setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }

    public ScooterBuilder setCurrentChargeLevel(int currentChargeLevel) {
        this.currentChargeLevel = currentChargeLevel;
        return this;
    }

    public ScooterBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ScooterBuilder setSponsorUserId(Integer sponsorUserId) {
        this.sponsorUserId = sponsorUserId;
        return this;
    }

    public ScooterBuilder setCurrentStationId(Integer currentStationId) {
        this.currentStationId = currentStationId;
        return this;
    }

    @Override
    public Scooter build() {
        return new Scooter(
                null,
                sponsorUserId,
                vehicleNumber,
                make,
                model,
                color,
                batteryCapacity,
                currentChargeLevel,
                status,
                currentStationId
        );
    }
}