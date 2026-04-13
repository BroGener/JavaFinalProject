package business.service.impl;

import business.model.GPSLog;
import business.service.TrackingService;
import data.datasource.DAOFactory;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TrackingService}.
 * Delegates all persistence operations to the DAO layer via {@link DAOFactory}.
 */
public class TrackingServiceImpl implements TrackingService {

    /**
     * @param log the {@link GPSLog} entry to save
     * @return the generated log ID
     * @throws Exception if a data access error occurs
     */
    public int saveLocation(GPSLog log) throws Exception {
        return DAOFactory.getTrackingDAO().insert(log);
    }

    /**
     * @param scooterId the ID of the scooter
     * @return an {@link Optional} containing the latest {@link GPSLog}, or empty if none found
     * @throws Exception if a data access error occurs
     */
    public Optional<GPSLog> getLatestLocation(int scooterId) throws Exception {
        return DAOFactory.getTrackingDAO().findLatestByScooterId(scooterId);
    }

    /**
     * @param scooterId the ID of the scooter
     * @return a chronological list of {@link GPSLog} entries for the given scooter
     * @throws Exception if a data access error occurs
     */
    public List<GPSLog> getLocationHistory(int scooterId) throws Exception {
        return DAOFactory.getTrackingDAO().findByScooterId(scooterId);
    }
}