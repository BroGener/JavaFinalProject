package data.daoimpl;

import business.model.GPSLog;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrackingDAO implements data.dao.TrackingDAO {
    public Integer insert(GPSLog entity) {
        entity.setLogId(MockDataStore.nextLogId());
        MockDataStore.LOGS.add(entity);
        return entity.getLogId();
    }
    public boolean update(GPSLog entity) { return true; }
    public boolean deleteById(Integer id) { return false; }
    public Optional<GPSLog> findById(Integer id) {
        for (GPSLog log : MockDataStore.LOGS) {
            if (log.getLogId().equals(id)) return Optional.of(log);
        }
        return Optional.empty();
    }
    public List<GPSLog> findAll() { return new ArrayList<GPSLog>(MockDataStore.LOGS); }
    public Optional<GPSLog> findLatestByScooterId(int scooterId) {
        GPSLog latest = null;
        for (GPSLog log : MockDataStore.LOGS) {
            if (log.getScooterId() != null && log.getScooterId() == scooterId) latest = log;
        }
        return Optional.ofNullable(latest);
    }
    public List<GPSLog> findByScooterId(int scooterId) {
        List<GPSLog> list = new ArrayList<GPSLog>();
        for (GPSLog log : MockDataStore.LOGS) {
            if (log.getScooterId() != null && log.getScooterId() == scooterId) list.add(log);
        }
        return list;
    }
}
