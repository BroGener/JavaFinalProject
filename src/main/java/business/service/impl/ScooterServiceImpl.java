package business.service.impl;

import business.model.Scooter;
import business.service.ScooterService;
import data.datasource.DAOFactory;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link ScooterService}.
 * Delegates all persistence operations to the DAO layer via {@link DAOFactory}.
 */
public class ScooterServiceImpl implements ScooterService {

    /**
     * @param scooter the scooter to register
     * @return the generated scooter ID
     * @throws Exception if a data access error occurs
     */
    public int registerScooter(Scooter scooter) throws Exception {
        return DAOFactory.getScooterDAO().insert(scooter);
    }

    /**
     * @param scooter the scooter with updated fields
     * @return {@code true} if the update was successful; {@code false} otherwise
     * @throws Exception if a data access error occurs
     */
    public boolean updateScooter(Scooter scooter) throws Exception {
        return DAOFactory.getScooterDAO().update(scooter);
    }

    /**
     * @param scooterId the ID of the scooter to retrieve
     * @return an {@link Optional} containing the {@link Scooter}, or empty if not found
     * @throws Exception if a data access error occurs
     */
    public Optional<Scooter> getScooterById(int scooterId) throws Exception {
        return DAOFactory.getScooterDAO().findById(scooterId);
    }

    /**
     * @return a list of all registered {@link Scooter}s
     * @throws Exception if a data access error occurs
     */
    public List<Scooter> getAllScooters() throws Exception {
        return DAOFactory.getScooterDAO().findAll();
    }

    /**
     * @param sponsorUserId the ID of the sponsor user
     * @return a list of {@link Scooter}s belonging to the given sponsor
     * @throws Exception if a data access error occurs
     */
    public List<Scooter> getScootersBySponsor(int sponsorUserId) throws Exception {
        return DAOFactory.getScooterDAO().findBySponsorId(sponsorUserId);
    }

    /**
     * Looks up the scooter by ID, updates its status, and persists the change.
     * Silently swallows exceptions thrown during the update inside the lambda.
     *
     * @param scooterId the ID of the scooter to update
     * @param status    the new status (e.g., {@code "AVAILABLE"}, {@code "IN_USE"}, {@code "MAINTENANCE"})
     * @throws Exception if a data access error occurs during the lookup
     */
    public void updateScooterStatus(int scooterId, String status) throws Exception {
        DAOFactory.getScooterDAO().findById(scooterId).ifPresent(s -> {
            s.setStatus(status);
            try {
                DAOFactory.getScooterDAO().update(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param stationId the ID of the charging station
     * @return a list of {@link Scooter}s currently docked at the given station
     * @throws Exception if a data access error occurs
     */
    public List<Scooter> getScootersByStation(int stationId) throws Exception {
        return DAOFactory.getScooterDAO().findByStationId(stationId);
    }

    /**
     * Looks up the scooter by ID, updates its station assignment, and persists the change.
     * Silently swallows exceptions thrown during the update inside the lambda.
     *
     * @param scooterId the ID of the scooter to update
     * @param stationId the ID of the station to assign the scooter to
     * @throws Exception if a data access error occurs during the lookup
     */
    public void updateScooterStation(int scooterId, int stationId) throws Exception {
        DAOFactory.getScooterDAO().findById(scooterId).ifPresent(s -> {
            s.setCurrentStationId(stationId);
            try {
                DAOFactory.getScooterDAO().update(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}