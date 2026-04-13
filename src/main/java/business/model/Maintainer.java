package business.model;

/**
 * Represents a maintainer user in the system.
 * <p>
 * A {@code Maintainer} is a specialized {@link User} responsible for the
 * upkeep, repair, and servicing of scooters. This class extends {@link User}
 * and automatically assigns the {@code "MAINTAINER"} role upon construction.
 * </p>
 *
 * @see User
 */
public class Maintainer extends User {

    /**
     * Default no-argument constructor.
     * <p>
     * Calls the parent {@link User} default constructor and explicitly sets
     * the role to {@code "MAINTAINER"}. Required for frameworks that
     * instantiate objects reflectively (e.g., ORM or serialization libraries).
     * </p>
     */
    public Maintainer() {
        super();
        setRole("MAINTAINER");
    }

    /**
     * Constructs a fully initialized {@code Maintainer} with the specified details.
     * <p>
     * The role is automatically set to {@code "MAINTAINER"} via the parent constructor.
     * </p>
     *
     * @param userId   the unique identifier for this maintainer, or {@code null} if not yet persisted
     * @param name     the full name of the maintainer
     * @param email    the email address of the maintainer
     * @param password the password for the maintainer's account
     */
    public Maintainer(Integer userId, String name, String email, String password) {
        super(userId, name, email, password, "MAINTAINER");
    }
}