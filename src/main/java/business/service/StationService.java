package business.service;

import business.model.ChargingStation;
import business.model.StationReport;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for querying charging station data and reports.
 *
 * @see ChargingStation
 * @see StationReport
 */
public interface StationService {

    /**
     * Retrieves a charging station by its ID.
     *
     * @param stationId the ID of the station to retrieve
     * @return an {@link Optional} containing the {@link ChargingStation}, or empty if not found
     * @throws Exception if a data access error occurs
     */
    Optional<ChargingStation> getStationById(int stationId) throws Exception;

    /**
     * Retrieves all charging stations registered in the system.
     *
     * @return a list of all {@link ChargingStation}s
     * @throws Exception if a data access error occurs
     */
    List<ChargingStation> getAllStations() throws Exception;

    /**
     * Retrieves availability and battery status reports for all charging stations.
     *
     * @return a list of {@link StationReport}s for all stations
     * @throws Exception if a data access error occurs
     */
    List<StationReport> getStationReports() throws Exception;
}