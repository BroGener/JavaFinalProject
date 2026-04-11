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
}
