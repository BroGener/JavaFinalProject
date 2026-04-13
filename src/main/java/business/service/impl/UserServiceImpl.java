package business.service.impl;

import business.model.User;
import business.service.UserService;
import data.datasource.DAOFactory;
import java.util.Optional;

/**
 * Implementation of UserService.
 * Provides methods to retrieve user data.
 */
public class UserServiceImpl implements UserService {

    /**
     * Gets a user by ID.
     *
     * @param userId the ID of the user
     * @return an Optional containing the user if found
     * @throws Exception if an error occurs while accessing the data source
     */
    public Optional<User> getUserById(int userId) throws Exception {
        // Find and return the user with the given ID
        return DAOFactory.getUserDAO().findById(userId);
    }

    /**
     * Gets a user by email.
     *
     * @param email the email of the user
     * @return an Optional containing the user if found
     * @throws Exception if an error occurs while accessing the data source
     */
    public Optional<User> getUserByEmail(String email) throws Exception {
        // Find and return the user with the given email
        return DAOFactory.getUserDAO().findByEmail(email);
    }
}