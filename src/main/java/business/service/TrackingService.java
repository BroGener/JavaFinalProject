package business.service;

import business.model.GPSLog;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for recording and retrieving scooter GPS location data.
 *
 * @see GPSLog
 */
public interface TrackingService {

    /**
     * Persists a new GPS log entry for a scooter.
     *
     * @param log the {@link GPSLog} entry to save
     * @return the generated log ID
     * @throws Exception if a data access error occurs
     */
    int saveLocation(GPSLog log) throws Exception;

    /**
     * Retrieves the most recent GPS log entry for a scooter.
     *
     * @param scooterId the ID of the scooter
     * @return an {@link Optional} containing the latest {@link GPSLog}, or empty if none found
     * @throws Exception if a data access error occurs
     */
    Optional<GPSLog> getLatestLocation(int scooterId) throws Exception;

    /**
     * Retrieves the full GPS location history for a scooter.
     *
     * @param scooterId the ID of the scooter
     * @return a list of {@link GPSLog} entries ordered chronologically
     * @throws Exception if a data access error occurs
     */
    List<GPSLog> getLocationHistory(int scooterId) throws Exception;
}