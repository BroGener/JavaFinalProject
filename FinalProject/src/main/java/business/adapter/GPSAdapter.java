package business.adapter;

/**
 * Adapter class that bridges the incompatible {@link ExternalGPSDevice}
 * (Adaptee) to the application's {@link GPSDataProvider} (Target) interface.
 *
 * <p><b>Design Pattern — Adapter (Object Adapter):</b><br>
 * <ul>
 *   <li><b>Target:</b> {@link GPSDataProvider}</li>
 *   <li><b>Adaptee:</b> {@link ExternalGPSDevice}</li>
 *   <li><b>Adapter:</b> {@link GPSAdapter} (this class)</li>
 * </ul>
 *
 * <p>The GPS device returns coordinates as a raw string ({@code "lat,lng"}) and
 * uses non-standard method names. This adapter parses those values and exposes
 * them through the standard interface the rest of the system expects.
 *
 * @author Albin (Member C)
 */
public class GPSAdapter implements GPSDataProvider {

    /** The wrapped external GPS device (Adaptee). */
    private final ExternalGPSDevice externalGPSDevice;

    /**
     * Constructs a GPSAdapter wrapping the given external device.
     *
     * @param externalGPSDevice the GPS hardware/vendor object to adapt
     */
    public GPSAdapter(ExternalGPSDevice externalGPSDevice) {
        this.externalGPSDevice = externalGPSDevice;
    }

    /**
     * Parses and returns the latitude from the device's raw coordinate string.
     *
     * @return latitude in decimal degrees
     */
    @Override
    public double getLatitude() {
        String[] parts = externalGPSDevice.fetchCoordinates().split(",");
        return Double.parseDouble(parts[0].trim());
    }

    /**
     * Parses and returns the longitude from the device's raw coordinate string.
     *
     * @return longitude in decimal degrees
     */
    @Override
    public double getLongitude() {
        String[] parts = externalGPSDevice.fetchCoordinates().split(",");
        return Double.parseDouble(parts[1].trim());
    }

    /**
     * Translates the device's {@code moving()} method to the standard
     * {@code isInTransit()} contract.
     *
     * @return true if the scooter is currently moving
     */
    @Override
    public boolean isInTransit() {
        return externalGPSDevice.moving();
    }

    /**
     * Translates the device's vendor-specific {@code stationCode()} to
     * the application's station ID convention.
     *
     * @return nearest station ID as an Integer
     */
    @Override
    public Integer getNearestStationId() {
        return externalGPSDevice.stationCode();
    }
}
