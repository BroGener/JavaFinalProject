package data.daoimpl;

import business.model.ChargingStation;
import business.model.StationReport;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StationDAO implements data.dao.StationDAO {
    public Integer insert(ChargingStation entity) { return entity.getStationId(); }
    public boolean update(ChargingStation entity) { return true; }
    public boolean deleteById(Integer id) { return false; }
    public Optional<ChargingStation> findById(Integer id) {
        for (ChargingStation station : MockDataStore.STATIONS) {
            if (station.getStationId().equals(id)) return Optional.of(station);
        }
        return Optional.empty();
    }
    public List<ChargingStation> findAll() { return new ArrayList<ChargingStation>(MockDataStore.STATIONS); }
    public List<StationReport> findStationReports() { return MockDataStore.getStationReports(); }
}
