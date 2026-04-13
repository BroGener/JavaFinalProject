package business.builder;

import business.model.Scooter;

/**
 * Concrete builder for constructing {@link Scooter} instances.
 * <p>
 * This class implements the <b>Builder design pattern</b> to assemble a
 * {@link Scooter} object field by field before producing the final instance
 * via {@link #build()}. Each setter returns {@code this}, enabling fluent
 * method chaining.
 * </p>
 *
 * <p><b>Note:</b> The following fields are retained in the builder for
 * interface compatibility but are not required by the current business logic:</p>
 * <ul>
 *   <li>{@code color} — not needed</li>
 *   <li>{@code currentChargeLevel} — not needed</li>
 *   <li>{@code currentStationId} — not needed</li>
 * </ul>
 *
 * @see Builder
 * @see Scooter
 */
public class ScooterBuilder implements Builder<Scooter> {

    /** The unique vehicle number identifying the scooter. */
    private String vehicleNumber;

    /** The manufacturer/brand of the scooter. */
    private String make;

    /** The model name of the scooter. */
    private String model;

    /** The color of the scooter. (not needed by current business logic) */
    private String color;

    /** The total battery capacity of the scooter in appropriate units. */
    private int batteryCapacity;

    /** The current charge level of the scooter's battery. (not needed by current business logic) */
    private int currentChargeLevel;

    /** The operational status of the scooter (e.g., available, in-use, maintenance). */
    private String status;

    /** The user ID of the sponsor associated with this scooter, if any. */
    private Integer sponsorUserId;

    /** The ID of the station where the scooter is currently located. (not needed by current business logic) */
    private Integer currentStationId;

    /**
     * Sets the vehicle number of the scooter.
     *
     * @param vehicleNumber the unique identifier for the vehicle
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    /**
     * Sets the make (manufacturer) of the scooter.
     *
     * @param make the brand or manufacturer name
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    /**
     * Sets the model name of the scooter.
     *
     * @param model the model designation
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * Sets the color of the scooter.
     * <p><b>Note:</b> This field is not required by the current business logic.</p>
     *
     * @param color the color description of the scooter
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Sets the total battery capacity of the scooter.
     *
     * @param batteryCapacity the battery capacity value
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }

    /**
     * Sets the current charge level of the scooter's battery.
     * <p><b>Note:</b> This field is not required by the current business logic.</p>
     *
     * @param currentChargeLevel the current battery charge level
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setCurrentChargeLevel(int currentChargeLevel) {
        this.currentChargeLevel = currentChargeLevel;
        return this;
    }

    /**
     * Sets the operational status of the scooter.
     *
     * @param status a string representing the scooter's current status
     *               (e.g., {@code "available"}, {@code "in-use"}, {@code "maintenance"})
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the sponsor user ID associated with this scooter.
     *
     * @param sponsorUserId the ID of the sponsoring user, or {@code null} if none
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setSponsorUserId(Integer sponsorUserId) {
        this.sponsorUserId = sponsorUserId;
        return this;
    }

    /**
     * Sets the current station ID where the scooter is located.
     * <p><b>Note:</b> This field is not required by the current business logic.</p>
     *
     * @param currentStationId the ID of the current station, or {@code null} if none
     * @return this {@code ScooterBuilder} instance for method chaining
     */
    public ScooterBuilder setCurrentStationId(Integer currentStationId) {
        this.currentStationId = currentStationId;
        return this;
    }

    /**
     * Constructs and returns a new {@link Scooter} instance from the current
     * builder state.
     * <p>
     * The scooter ID is set to {@code null} as it is expected to be assigned
     * by the persistence layer upon saving.
     * </p>
     *
     * @return a new {@link Scooter} object populated with this builder's field values
     */
    @Override
    public Scooter build() {
        return new Scooter(
                null,            // id — assigned by the persistence layer
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