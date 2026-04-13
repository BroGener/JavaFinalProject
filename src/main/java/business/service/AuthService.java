package business.service;

import business.model.User;
import java.util.Optional;

/**
 * Service interface for user authentication and registration.
 *
 * @see User
 */
public interface AuthService {

    /**
     * Authenticates a user by email and password.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return an {@link Optional} containing the matched {@link User}, or empty if credentials are invalid
     * @throws Exception if a data access error occurs
     */
    Optional<User> login(String email, String password) throws Exception;

    /**
     * Registers a new user account.
     *
     * @param name     the user's full name
     * @param email    the user's email address
     * @param password the user's password
     * @param role     the role to assign (e.g., {@code "USER"}, {@code "SPONSOR"}, {@code "MAINTAINER"})
     * @return the generated user ID of the newly registered user
     * @throws Exception if a data access error occurs or the email is already in use
     */
    int register(String name, String email, String password, String role) throws Exception;
}