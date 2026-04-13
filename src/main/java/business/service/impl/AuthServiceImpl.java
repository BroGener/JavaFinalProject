package business.service.impl;

import business.factory.UserFactory;
import business.model.User;
import business.service.AuthService;
import data.datasource.DAOFactory;
import java.util.Optional;

/**
 * Implementation of {@link AuthService} for user login and registration.
 * Delegates persistence operations to the DAO layer via {@link DAOFactory}.
 */
public class AuthServiceImpl implements AuthService {

    /**
     * Authenticates a user by delegating credential lookup to the DAO layer.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return an {@link Optional} containing the matched {@link User}, or empty if credentials are invalid
     * @throws Exception if a data access error occurs
     */
    public Optional<User> login(String email, String password) throws Exception {
        return DAOFactory.getUserDAO().findByEmailAndPassword(email, password);
    }

    /**
     * Creates a new user via {@link UserFactory} and persists it via the DAO layer.
     *
     * @param name     the user's full name
     * @param email    the user's email address
     * @param password the user's password
     * @param role     the role to assign (e.g., {@code "USER"}, {@code "SPONSOR"}, {@code "MAINTAINER"})
     * @return the generated user ID of the newly registered user
     * @throws Exception if a data access error occurs or the email is already in use
     */
    public int register(String name, String email, String password, String role) throws Exception {
        User user = UserFactory.createUser(role, name, email, password);
        return DAOFactory.getUserDAO().insert(user);
    }
}