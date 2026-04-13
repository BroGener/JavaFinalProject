package business.service.impl;

import business.model.ChargingStation;
import business.model.StationReport;
import business.service.StationService;
import data.datasource.DAOFactory;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StationService}.
 * Delegates all persistence operations to the DAO layer via {@link DAOFactory}.
 */
public class StationServiceImpl implements StationService {

    /**
     * @param stationId the ID of the station to retrieve
     * @return an {@link Optional} containing the {@link ChargingStation}, or empty if not found
     * @throws Exception if a data access error occurs
     */
    public Optional<ChargingStation> getStationById(int stationId) throws Exception {
        return DAOFactory.getStationDAO().findById(stationId);
    }

    /**
     * @return a list of all registered {@link ChargingStation}s
     * @throws Exception if a data access error occurs
     */
    public List<ChargingStation> getAllStations() throws Exception {
        return DAOFactory.getStationDAO().findAll();
    }

    /**
     * @return a list of {@link StationReport}s for all charging stations
     * @throws Exception if a data access error occurs
     */
    public List<StationReport> getStationReports() throws Exception {
        return DAOFactory.getStationDAO().findStationReports();
    }
}