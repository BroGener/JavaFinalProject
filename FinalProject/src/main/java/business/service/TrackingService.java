package business.service;

import business.adapter.ExternalGPSDevice;
import business.adapter.GPSAdapter;
import business.adapter.GPSDataProvider;
import business.model.GPSLog;
import business.service.TrackingService;
import data.dao.TrackingDAO;
import data.daoimpl.MySQLTrackingDAO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TrackingService}.
 * Uses the Adapter pattern ({@link GPSAdapter}) to normalise GPS data from
 * an external device before persisting it via {@link TrackingDAO}.
 *
 * @author Albin 
 */
public class TrackingService {

    private final TrackingDAO trackingDAO;
    private final GPSDataProvider gpsProvider;

    /** Default constructor: uses MySQL DAO and real GPS adapter. */
    public TrackingService() {
        this.trackingDAO = new MySQLTrackingDAO();
        this.gpsProvider = new GPSAdapter(new ExternalGPSDevice());
    }

    /**
     * Constructor allowing injection for testing.
     *
     * @param trackingDAO the DAO to use
     * @param gpsProvider the GPS data provider (adapter)
     */
    public TrackingService(TrackingDAO trackingDAO, GPSDataProvider gpsProvider) {
        this.trackingDAO = trackingDAO;
        this.gpsProvider = gpsProvider;
    }

    /**
     * Saves a GPS location log for a scooter.
     *
     * @param log the GPSLog to persist
     * @return the generated gps_log_id
     * @throws Exception on data access error
     */
    
    public int saveLocation(GPSLog log) throws Exception {
        return trackingDAO.insert(log);
    }

    /**
     * Returns the most recent GPS log for a scooter.
     *
     * @param scooterId the scooter to query
     * @return Optional with the latest GPSLog, or empty
     * @throws Exception on data access error
     */
    @Override
    public Optional<GPSLog> getLatestLocation(int scooterId) throws Exception {
        return trackingDAO.findLatestByScooterId(scooterId);
    }

    /**
     * Returns full location history for a scooter.
     *
     * @param scooterId the scooter to query
     * @return list of GPSLog entries, newest first
     * @throws Exception on data access error
     */
    @Override
    public List<GPSLog> getLocationHistory(int scooterId) throws Exception {
        return trackingDAO.findByScooterId(scooterId);
    }

    /**
     * Reads a fresh GPS reading from the external device (via Adapter) and
     * saves it as a new log for the given scooter.
     *
     * @param scooterId the scooter to update
     * @return generated gps_log_id
     * @throws Exception on data access error
     */
    public int recordLiveLocation(int scooterId) throws Exception {
        GPSLog log = new GPSLog();
        log.setScooterId(scooterId);
        log.setLatitude(gpsProvider.getLatitude());
        log.setLongitude(gpsProvider.getLongitude());
        log.setInTransit(gpsProvider.isInTransit());
        log.setStationId(gpsProvider.getNearestStationId());
        log.setLogTime(new Timestamp(System.currentTimeMillis()));
        return trackingDAO.insert(log);
    }
}
