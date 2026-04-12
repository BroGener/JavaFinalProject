package business.service;

import business.model.ChargingStation;
import business.model.StationReport;
import business.service.StationService;
import data.dao.StationDAO;
import data.daoimpl.MySQLStationDAO;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of StationService.
 *
 * @author Albin (Member C)
 */
public class StationService{

    private final StationDAO stationDAO;

    public StationService() {
        this.stationDAO = new MySQLStationDAO();
    }

    public StationService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    @Override
    public Optional<ChargingStation> getStationById(int stationId) throws Exception {
        return stationDAO.findById(stationId);
    }

    @Override
    public List<ChargingStation> getAllStations() throws Exception {
        return stationDAO.findAll();
    }

    /** FR-06: scooter distribution across all stations. */
    @Override
    public List<StationReport> getStationReports() throws Exception {
        return stationDAO.findStationReports();
    }
}