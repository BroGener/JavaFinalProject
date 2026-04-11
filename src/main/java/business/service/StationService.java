package business.service;

import business.model.ChargingStation;
import business.model.StationReport;
import java.util.List;
import java.util.Optional;

public interface StationService {
    Optional<ChargingStation> getStationById(int stationId) throws Exception;
    List<ChargingStation> getAllStations() throws Exception;
    List<StationReport> getStationReports() throws Exception;
}