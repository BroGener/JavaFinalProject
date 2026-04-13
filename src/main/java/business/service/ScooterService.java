package business.service;

import business.model.Scooter;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing scooter registration and operations.
 *
 * @see Scooter
 */
public interface ScooterService {

    /**
     * Registers a new scooter and persists it.
     *
     * @param scooter the scooter to register
     * @return the generated scooter ID
     * @throws Exception if a data access error occurs
     */
    int registerScooter(Scooter scooter) throws Exception;

    /**
     * Updates an existing scooter's details.
     *
     * @param scooter the scooter with updated fields
     * @return {@code true} if the update was successful; {@code false} otherwise
     * @throws Exception if a data access error occurs
     */
    boolean updateScooter(Scooter scooter) throws Exception;

    /**
     * Retrieves a scooter by its ID.
     *
     * @param scooterId the ID of the scooter to retrieve
     * @return an {@link Optional} containing the {@link Scooter}, or empty if not found
     * @throws Exception if a data access error occurs
     */
    Optional<Scooter> getScooterById(int scooterId) throws Exception;

    /**
     * Retrieves all scooters registered in the system.
     *
     * @return a list of all {@link Scooter}s
     * @throws Exception if a data access error occurs
     */
    List<Scooter> getAllScooters() throws Exception;

    /**
     * Retrieves all scooters associated with a specific sponsor user.
     *
     * @param sponsorUserId the ID of the sponsor user
     * @return a list of {@link Scooter}s belonging to the given sponsor
     * @throws Exception if a data access error occurs
     */
    List<Scooter> getScootersBySponsor(int sponsorUserId) throws Exception;

    /**
     * Updates the operational status of a scooter.
     *
     * @param scooterId the ID of the scooter to update
     * @param status    the new status (e.g., {@code "AVAILABLE"}, {@code "IN_USE"}, {@code "MAINTENANCE"})
     * @throws Exception if a data access error occurs
     */
    void updateScooterStatus(int scooterId, String status) throws Exception;

    /**
     * Retrieves all scooters currently docked at a specific charging station.
     *
     * @param stationId the ID of the charging station
     * @return a list of {@link Scooter}s at the given station
     * @throws Exception if a data access error occurs
     */
    List<Scooter> getScootersByStation(int stationId) throws Exception;

    /**
     * Updates the current station assignment of a scooter.
     *
     * @param scooterId the ID of the scooter to update
     * @param stationId the ID of the station to assign the scooter to
     * @throws Exception if a data access error occurs
     */
    void updateScooterStation(int scooterId, int stationId) throws Exception;
}