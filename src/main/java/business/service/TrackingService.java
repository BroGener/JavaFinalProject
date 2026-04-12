package business.service;

import business.model.GPSLog;
import java.util.List;
import java.util.Optional;

public interface TrackingService {
    int saveLocation(GPSLog log) throws Exception;
    Optional<GPSLog> getLatestLocation(int scooterId) throws Exception;
    List<GPSLog> getLocationHistory(int scooterId) throws Exception;
}