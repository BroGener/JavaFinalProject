package business.service.impl;

import business.model.ChargingStation;
import business.model.StationReport;
import business.service.StationService;
import data.datasource.DAOFactory;
import java.util.List;
import java.util.Optional;

public class StationServiceImpl implements StationService {
    public Optional<ChargingStation> getStationById(int stationId) throws Exception { return DAOFactory.getStationDAO().findById(stationId); }
    public List<ChargingStation> getAllStations() throws Exception { return DAOFactory.getStationDAO().findAll(); }
    public List<StationReport> getStationReports() throws Exception { return DAOFactory.getStationDAO().findStationReports(); }
}
