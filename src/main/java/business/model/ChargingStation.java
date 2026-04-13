package business.model;

import java.io.Serializable;

/**
 * Represents a scooter charging station in the system.
 * <p>
 * A {@code ChargingStation} holds information about a physical docking and
 * charging location, including its name, geographic location, total slot
 * capacity, and the number of slots currently available. This class implements
 * {@link Serializable} to support persistence and data transfer.
 * </p>
 */
public class ChargingStation implements Serializable {

    /** The unique identifier for this charging station. */
    private Integer stationId;

    /** The human-readable name of this charging station. */
    private String stationName;

    /** The geographic location or address of this charging station. */
    private String location;

    /** The total number of scooter slots at this charging station. */
    private int capacity;

    /** The number of scooter slots currently available at this charging station. */
    private int availableSlots;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public ChargingStation() {}

    /**
     * Constructs a fully initialized {@code ChargingStation} with all fields specified.
     *
     * @param stationId      the unique identifier for this station, or {@code null} if not yet persisted
     * @param stationName    the human-readable name of the station
     * @param location       the geographic location or address of the station
     * @param capacity       the total number of scooter slots at the station
     * @param availableSlots the number of slots currently available
     */
    public ChargingStation(Integer stationId, String stationName, String location,
                           int capacity, int availableSlots) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.location = location;
        this.capacity = capacity;
        this.availableSlots = availableSlots;
    }

    /**
     * Returns the unique identifier of this charging station.
     *
     * @return the station ID, or {@code null} if not yet persisted
     */
    public Integer getStationId() { return stationId; }

    /**
     * Sets the unique identifier of this charging station.
     *
     * @param stationId the station ID to assign
     */
    public void setStationId(Integer stationId) { this.stationId = stationId; }

    /**
     * Returns the human-readable name of this charging station.
     *
     * @return the station name
     */
    public String getStationName() { return stationName; }

    /**
     * Sets the human-readable name of this charging station.
     *
     * @param stationName the station name to assign
     */
    public void setStationName(String stationName) { this.stationName = stationName; }

    /**
     * Returns the geographic location or address of this charging station.
     *
     * @return the location string
     */
    public String getLocation() { return location; }

    /**
     * Sets the geographic location or address of this charging station.
     *
     * @param location the location string to assign
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Returns the total number of scooter slots at this charging station.
     *
     * @return the total slot capacity
     */
    public int getCapacity() { return capacity; }

    /**
     * Sets the total number of scooter slots at this charging station.
     *
     * @param capacity the total slot capacity to assign
     */
    public void setCapacity(int capacity) { this.capacity = capacity; }

    /**
     * Returns the number of scooter slots currently available at this charging station.
     *
     * @return the number of available slots
     */
    public int getAvailableSlots() { return availableSlots; }

    /**
     * Sets the number of scooter slots currently available at this charging station.
     *
     * @param availableSlots the number of available slots to assign
     */
    public void setAvailableSlots(int availableSlots) { this.availableSlots = availableSlots; }
}