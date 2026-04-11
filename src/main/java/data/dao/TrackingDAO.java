package data.dao;

import business.model.GPSLog;
import java.util.List;
import java.util.Optional;

public interface TrackingDAO extends GenericDAO<GPSLog, Integer> {
    Optional<GPSLog> findLatestByScooterId(int scooterId) throws Exception;
    List<GPSLog> findByScooterId(int scooterId) throws Exception;
}