package business.factory;

import business.model.Maintainer;
import business.model.Sponsor;
import business.model.User;

/**
 * Factory class for creating {@link User} instances based on a specified role.
 * <p>
 * This class follows the <b>Factory design pattern</b>, centralizing the
 * instantiation logic for different user types. Callers provide a role string
 * and the factory returns the appropriate {@link User} subclass without
 * exposing the construction details.
 * </p>
 *
 * <p>This class is non-instantiable; all methods are static.</p>
 *
 * @see User
 * @see Sponsor
 * @see Maintainer
 */
public final class UserFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private UserFactory() {
    }

    /**
     * Creates and returns a {@link User} instance corresponding to the given role.
     * <p>
     * The {@code role} parameter is case-insensitive. Supported roles are:
     * </p>
     * <ul>
     *   <li>{@code "SPONSOR"} — returns a new {@link Sponsor} instance</li>
     *   <li>{@code "MAINTAINER"} — returns a new {@link Maintainer} instance</li>
     *   <li>Any other value — returns a base {@link User} instance with role {@code "USER"}</li>
     * </ul>
     * <p>
     * The {@code id} field is set to {@code null} for all created users, as it
     * is expected to be assigned by the persistence layer upon saving.
     * </p>
     *
     * @param role     the role designation for the user (e.g., {@code "SPONSOR"}, {@code "MAINTAINER"})
     * @param name     the full name of the user
     * @param email    the email address of the user
     * @param password the password for the user's account
     * @return a {@link User} instance of the appropriate subtype for the given role
     */
    public static User createUser(String role, String name, String email, String password) {
        switch (role.toUpperCase()) {
            case "SPONSOR":
                // Sponsor is a privileged user who can associate with scooters
                return new Sponsor(null, name, email, password);
            case "MAINTAINER":
                // Maintainer is responsible for scooter upkeep and repairs
                return new Maintainer(null, name, email, password);
            default:
                // Fallback to a base User with the generic "USER" role
                return new User(null, name, email, password, "USER");
        }
    }
}