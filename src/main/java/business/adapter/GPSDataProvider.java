package business.adapter;

/**
 * Standard interface for GPS data providers within the application.
 * <p>
 * This interface defines a unified contract for retrieving location and
 * movement information from any GPS source. By programming to this interface,
 * the rest of the application remains decoupled from any specific GPS
 * implementation or third-party device API.
 * </p>
 *
 * @see GPSAdapter
 * @see ExternalGPSDevice
 */
public interface GPSDataProvider {

    /**
     * Returns the current latitude of the GPS device.
     *
     * @return the latitude as a {@code double}, in decimal degrees
     */
    double getLatitude();

    /**
     * Returns the current longitude of the GPS device.
     *
     * @return the longitude as a {@code double}, in decimal degrees
     */
    double getLongitude();

    /**
     * Indicates whether the GPS device is currently in transit.
     *
     * @return {@code true} if the device is moving; {@code false} if stationary
     */
    boolean isInTransit();

    /**
     * Returns the identifier of the nearest station to the device's current position.
     *
     * @return an {@link Integer} representing the nearest station ID,
     *         or {@code null} if no station is associated
     */
    Integer getNearestStationId();
}