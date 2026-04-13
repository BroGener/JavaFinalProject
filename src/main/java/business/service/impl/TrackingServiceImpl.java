package business.service.impl;

import business.model.GPSLog;
import business.service.TrackingService;
import data.datasource.DAOFactory;
import java.util.List;
import java.util.Optional;

public class TrackingServiceImpl implements TrackingService {
    public int saveLocation(GPSLog log) throws Exception { return DAOFactory.getTrackingDAO().insert(log); }
    public Optional<GPSLog> getLatestLocation(int scooterId) throws Exception { return DAOFactory.getTrackingDAO().findLatestByScooterId(scooterId); }
    public List<GPSLog> getLocationHistory(int scooterId) throws Exception { return DAOFactory.getTrackingDAO().findByScooterId(scooterId); }
    
    public GPSLog simulateGPS(int scooterId) {
    GPSLog log = new GPSLog();

    log.setScooterId(scooterId);

    // fake coordinates (Ottawa area)
    double lat = 45.4215 + (Math.random() - 0.5) / 100;
    double lng = -75.6972 + (Math.random() - 0.5) / 100;

    log.setLatitude(lat);
    log.setLongitude(lng);

    log.setRecordedAt(java.time.LocalDateTime.now());
    return log;
}
    @Override
    public GPSLog simulateAndSave(int scooterId) throws Exception {
    GPSLog log = simulateGPS(scooterId);

    // save to database
    DAOFactory.getTrackingDAO().insert(log);

    return log;
}
}
