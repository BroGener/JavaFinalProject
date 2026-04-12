package data.dao;

import business.model.ChargingStation;
import business.model.StationReport;
import java.util.List;

/**
 * DAO interface for ChargingStation operations.
 *
 * @author Albin 
 */
public interface StationDAO extends GenericDAO<ChargingStation, Integer> {

    /**
     * Builds a report for every station showing
     * how many scooters are currently docked there.
     *
     * @return list of StationReport DTOs
     * @throws Exception on data access error
     */
    List<StationReport> findStationReports() throws Exception;
}