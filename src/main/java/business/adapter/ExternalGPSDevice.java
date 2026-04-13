package business.adapter;

/**
 * Simulates an external GPS device with a proprietary interface.
 * <p>
 * This class represents a third-party GPS device that provides raw location
 * data, movement status, and station identification through its own API.
 * It is typically wrapped by an adapter to conform to a standard interface.
 * </p>
 */
public class ExternalGPSDevice {

    /**
     * Fetches the current GPS coordinates from the device.
     * <p>
     * Returns a raw coordinate string in the format {@code "latitude,longitude"}.
     * </p>
     *
     * @return a {@code String} containing the latitude and longitude separated
     *         by a comma (e.g., {@code "45.3499,-75.7561"})
     */
    public String fetchCoordinates() {
        return "45.3499,-75.7561";
    }

    /**
     * Indicates whether the GPS device is currently detecting movement.
     *
     * @return {@code true} if the device is in motion; {@code false} if stationary
     */
    public boolean moving() {
        return false;
    }

    /**
     * Returns the station code associated with the current GPS location.
     * <p>
     * The station code identifies the nearest or assigned station to the device's
     * current position.
     * </p>
     *
     * @return an {@code int} representing the station identifier
     */
    public int stationCode() {
        return 1;
    }
}