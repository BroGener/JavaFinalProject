package business.adapter;

/**
 * Target interface for the Adapter design pattern.
 *
 * <p>Defines the standard contract that the application uses to obtain GPS
 * data from any source. The {@link GPSAdapter} adapts the incompatible
 * {@link ExternalGPSDevice} format into this interface.
 *
 * @author Albin (Member C)
 */
public interface GPSDataProvider {

    /**
     * Returns the current latitude of the scooter.
     *
     * @return latitude in decimal degrees
     */
    double getLatitude();

    /**
     * Returns the current longitude of the scooter.
     *
     * @return longitude in decimal degrees
     */
    double getLongitude();

    /**
     * Returns whether the scooter is currently in transit
     * (i.e. moving, not docked at a station).
     *
     * @return true if in transit
     */
    boolean isInTransit();

    /**
     * Returns the ID of the nearest charging station, or null if unknown.
     *
     * @return station ID or null
     */
    Integer getNearestStationId();
}
