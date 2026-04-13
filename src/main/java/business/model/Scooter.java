package business.model;

import java.io.Serializable;

/**
 * Represents a scooter registered in the system.
 * <p>
 * A {@code Scooter} holds all physical and operational details of a scooter,
 * including its vehicle identification, manufacturer information, battery state,
 * current operational status, and its association with a sponsor user and
 * charging station. This class implements {@link Serializable} to support
 * persistence and data transfer.
 * </p>
 *
 * @see ScooterBuilder
 */
public class Scooter implements Serializable {

    /** The unique identifier for this scooter. */
    private Integer scooterId;

    /** The ID of the sponsor user associated with this scooter, if any. */
    private Integer sponsorUserId;

    /** The unique vehicle number identifying this scooter. */
    private String vehicleNumber;

    /** The manufacturer or brand of this scooter. */
    private String make;

    /** The model name of this scooter. */
    private String model;

    /** The color of this scooter. */
    private String color;

    /** The total battery capacity of this scooter. */
    private int batteryCapacity;

    /** The current battery charge level of this scooter. */
    private int currentChargeLevel;

    /** The operational status of this scooter (e.g., {@code "AVAILABLE"}, {@code "IN_USE"}, {@code "MAINTENANCE"}). */
    private String status;

    /** The ID of the charging station where this scooter is currently located, or {@code null} if not docked. */
    private Integer currentStationId;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public Scooter() {
    }

    /**
     * Constructs a fully initialized {@code Scooter} with all fields specified.
     *
     * @param scooterId          the unique identifier for this scooter, or {@code null} if not yet persisted
     * @param sponsorUserId      the ID of the associated sponsor user, or {@code null} if none
     * @param vehicleNumber      the unique vehicle number of this scooter
     * @param make               the manufacturer or brand of this scooter
     * @param model              the model name of this scooter
     * @param color              the color of this scooter
     * @param batteryCapacity    the total battery capacity of this scooter
     * @param currentChargeLevel the current battery charge level of this scooter
     * @param status             the operational status (e.g., {@code "AVAILABLE"}, {@code "IN_USE"}, {@code "MAINTENANCE"})
     * @param currentStationId   the ID of the current charging station, or {@code null} if not docked
     */
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

    /**
     * Returns the unique identifier of this scooter.
     *
     * @return the scooter ID, or {@code null} if not yet persisted
     */
    public Integer getScooterId() { return scooterId; }

    /**
     * Sets the unique identifier of this scooter.
     *
     * @param scooterId the scooter ID to assign
     */
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }

    /**
     * Returns the ID of the sponsor user associated with this scooter.
     *
     * @return the sponsor user ID, or {@code null} if none
     */
    public Integer getSponsorUserId() { return sponsorUserId; }

    /**
     * Sets the ID of the sponsor user associated with this scooter.
     *
     * @param sponsorUserId the sponsor user ID to assign, or {@code null} if none
     */
    public void setSponsorUserId(Integer sponsorUserId) { this.sponsorUserId = sponsorUserId; }

    /**
     * Returns the unique vehicle number of this scooter.
     *
     * @return the vehicle number
     */
    public String getVehicleNumber() { return vehicleNumber; }

    /**
     * Sets the unique vehicle number of this scooter.
     *
     * @param vehicleNumber the vehicle number to assign
     */
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    /**
     * Returns the manufacturer or brand of this scooter.
     *
     * @return the make
     */
    public String getMake() { return make; }

    /**
     * Sets the manufacturer or brand of this scooter.
     *
     * @param make the make to assign
     */
    public void setMake(String make) { this.make = make; }

    /**
     * Returns the model name of this scooter.
     *
     * @return the model name
     */
    public String getModel() { return model; }

    /**
     * Sets the model name of this scooter.
     *
     * @param model the model name to assign
     */
    public void setModel(String model) { this.model = model; }

    /**
     * Returns the color of this scooter.
     *
     * @return the color description
     */
    public String getColor() { return color; }

    /**
     * Sets the color of this scooter.
     *
     * @param color the color description to assign
     */
    public void setColor(String color) { this.color = color; }

    /**
     * Returns the total battery capacity of this scooter.
     *
     * @return the battery capacity
     */
    public int getBatteryCapacity() { return batteryCapacity; }

    /**
     * Sets the total battery capacity of this scooter.
     *
     * @param batteryCapacity the battery capacity to assign
     */
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }

    /**
     * Returns the current battery charge level of this scooter.
     *
     * @return the current charge level
     */
    public int getCurrentChargeLevel() { return currentChargeLevel; }

    /**
     * Sets the current battery charge level of this scooter.
     *
     * @param currentChargeLevel the current charge level to assign
     */
    public void setCurrentChargeLevel(int currentChargeLevel) { this.currentChargeLevel = currentChargeLevel; }

    /**
     * Returns the operational status of this scooter.
     *
     * @return the status (e.g., {@code "AVAILABLE"}, {@code "IN_USE"}, {@code "MAINTENANCE"})
     */
    public String getStatus() { return status; }

    /**
     * Sets the operational status of this scooter.
     *
     * @param status the status to assign (e.g., {@code "AVAILABLE"}, {@code "IN_USE"}, {@code "MAINTENANCE"})
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the ID of the charging station where this scooter is currently located.
     *
     * @return the current station ID, or {@code null} if the scooter is not docked
     */
    public Integer getCurrentStationId() { return currentStationId; }

    /**
     * Sets the ID of the charging station where this scooter is currently located.
     *
     * @param currentStationId the station ID to assign, or {@code null} if not docked
     */
    public void setCurrentStationId(Integer currentStationId) { this.currentStationId = currentStationId; }
}