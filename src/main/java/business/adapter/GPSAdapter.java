package business.adapter;

/**
 * Adapter that bridges {@link ExternalGPSDevice} to the {@link GPSDataProvider} interface.
 * <p>
 * This class follows the <b>Adapter design pattern</b>, translating the proprietary
 * API of {@link ExternalGPSDevice} into the standardized {@link GPSDataProvider}
 * interface expected by the rest of the application.
 * </p>
 */
public class GPSAdapter implements GPSDataProvider {

    /** The external GPS device instance being adapted. */
    private final ExternalGPSDevice externalGPSDevice;

    /**
     * Constructs a {@code GPSAdapter} wrapping the given external GPS device.
     *
     * @param externalGPSDevice the third-party GPS device to adapt; must not be {@code null}
     */
    public GPSAdapter(ExternalGPSDevice externalGPSDevice) {
        this.externalGPSDevice = externalGPSDevice;
    }

    /**
     * Returns the current latitude parsed from the device's raw coordinate string.
     * <p>
     * Calls {@link ExternalGPSDevice#fetchCoordinates()}, splits the result on
     * {@code ","}, and parses the first token as a {@code double}.
     * </p>
     *
     * @return the latitude component of the current GPS coordinates
     */
    @Override
    public double getLatitude() {
        String[] parts = externalGPSDevice.fetchCoordinates().split(",");
        return Double.parseDouble(parts[0]);
    }

    /**
     * Returns the current longitude parsed from the device's raw coordinate string.
     * <p>
     * Calls {@link ExternalGPSDevice#fetchCoordinates()}, splits the result on
     * {@code ","}, and parses the second token as a {@code double}.
     * </p>
     *
     * @return the longitude component of the current GPS coordinates
     */
    @Override
    public double getLongitude() {
        String[] parts = externalGPSDevice.fetchCoordinates().split(",");
        return Double.parseDouble(parts[1]);
    }

    /**
     * Indicates whether the device is currently in transit.
     * <p>
     * Delegates directly to {@link ExternalGPSDevice#moving()}.
     * </p>
     *
     * @return {@code true} if the device is moving; {@code false} if stationary
     */
    @Override
    public boolean isInTransit() {
        return externalGPSDevice.moving();
    }

    /**
     * Returns the identifier of the nearest station to the device's current position.
     * <p>
     * Delegates directly to {@link ExternalGPSDevice#stationCode()}.
     * </p>
     *
     * @return an {@link Integer} representing the nearest station ID
     */
    @Override
    public Integer getNearestStationId() {
        return externalGPSDevice.stationCode();
    }
}