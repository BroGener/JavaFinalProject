package data.daoimpl;

import business.model.GPSLog;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for GPS tracking operations.
 *
 * @author Albin (Member C)
 */
public interface TrackingDAO extends GenericDAO<GPSLog, Integer> {

    Optional<GPSLog> findLatestByScooterId(int scooterId) throws Exception;

    List<GPSLog> findByScooterId(int scooterId) throws Exception;
}