package data.dao;

import business.model.ChargingStation;
import business.model.StationReport;
import java.util.List;

public interface StationDAO extends GenericDAO<ChargingStation, Integer> {
    List<StationReport> findStationReports() throws Exception;
}