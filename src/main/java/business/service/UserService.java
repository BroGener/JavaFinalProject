package business.service;

import business.model.User;
import java.util.Optional;

/**
 * Service interface for retrieving user account data.
 *
 * @see User
 */
public interface UserService {

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an {@link Optional} containing the {@link User}, or empty if not found
     * @throws Exception if a data access error occurs
     */
    Optional<User> getUserById(int userId) throws Exception;

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address to look up
     * @return an {@link Optional} containing the {@link User}, or empty if not found
     * @throws Exception if a data access error occurs
     */
    Optional<User> getUserByEmail(String email) throws Exception;
}