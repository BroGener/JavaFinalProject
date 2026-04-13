package business.model;

/**
 * A {@link User} subclass representing a sponsor.
 * Automatically assigned the {@code "SPONSOR"} role on construction.
 */
public class Sponsor extends User {

    /** No-arg constructor; sets role to {@code "SPONSOR"}. */
    public Sponsor() {
        super();
        setRole("SPONSOR");
    }

    /**
     * @param userId   user ID, or {@code null} if not yet persisted
     * @param name     full name
     * @param email    email address
     * @param password account password
     */
    public Sponsor(Integer userId, String name, String email, String password) {
        super(userId, name, email, password, "SPONSOR");
    }
}