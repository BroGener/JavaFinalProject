package business.model;

import java.io.Serializable;

/**
 * Base class representing a system user.
 * Extended by {@link Sponsor}, {@link Maintainer}, and other role-specific subclasses.
 * Credit balance defaults to {@code 0.0} on construction.
 */
public class User implements Serializable {

    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private Double creditBalance; // account credit balance, defaults to 0.0

    /** No-arg constructor; initializes credit balance to {@code 0.0}. */
    public User() {
        this.creditBalance = 0.0;
    }

    /**
     * @param userId   user ID, or {@code null} if not yet persisted
     * @param name     full name
     * @param email    email address
     * @param password account password
     * @param role     role string (e.g., {@code "USER"}, {@code "SPONSOR"}, {@code "MAINTAINER"})
     */
    public User(Integer userId, String name, String email, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.creditBalance = 0.0;
    }

    /** @return the user ID, or {@code null} if not yet persisted */
    public Integer getUserId() { return userId; }
    /** @param userId the user ID to assign */
    public void setUserId(Integer userId) { this.userId = userId; }

    /** @return the user's full name */
    public String getName() { return name; }
    /** @param name the full name to assign */
    public void setName(String name) { this.name = name; }

    /** @return the user's email address */
    public String getEmail() { return email; }
    /** @param email the email address to assign */
    public void setEmail(String email) { this.email = email; }

    /** @return the user's password */
    public String getPassword() { return password; }
    /** @param password the password to assign */
    public void setPassword(String password) { this.password = password; }

    /** @return the user's role (e.g., {@code "USER"}, {@code "SPONSOR"}, {@code "MAINTAINER"}) */
    public String getRole() { return role; }
    /** @param role the role string to assign */
    public void setRole(String role) { this.role = role; }

    /** @return the user's current credit balance */
    public Double getCreditBalance() { return creditBalance; }
    /** @param creditBalance the credit balance to assign */
    public void setCreditBalance(Double creditBalance) { this.creditBalance = creditBalance; }
}