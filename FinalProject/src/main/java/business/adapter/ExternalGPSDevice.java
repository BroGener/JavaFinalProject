package business.adapter;

/**
 * Adaptee class representing an external (third-party) GPS device.
 *
 * <p>This class uses an incompatible interface — it returns coordinates as a
 * raw comma-separated string and uses different method names. The
 * {@link GPSAdapter} wraps this class to make it compatible with
 * {@link GPSDataProvider}.
 *
 * <p>In a real system this class would communicate with physical GPS hardware
 * or a vendor SDK. For the prototype it returns fixed sample coordinates for
 * Algonquin College's Woodroffe Campus.
 *
 * @author Albin 
 */
public class ExternalGPSDevice {

    /**
     * Returns raw GPS coordinates as "latitude,longitude".
     * Simulates a reading near Algonquin College's C Building.
     *
     * @return comma-separated coordinate string
     */
    public String fetchCoordinates() {
        // Sample: Algonquin College Woodroffe Campus centre
        return "45.3499,-75.7561";
    }

    /**
     * Returns whether the scooter hardware reports movement.
     *
     * @return true if the scooter is physically moving
     */
    public boolean moving() {
        return false;
    }

    /**
     * Returns the vendor-specific station code for the nearest docking point.
     *
     * @return station code integer
     */
    public int stationCode() {
        return 1;
    }
}
