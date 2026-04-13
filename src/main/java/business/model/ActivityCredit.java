package business.model;

import java.io.Serializable;

/**
 * Represents a credit entry associated with a specific activity.
 * <p>
 * An {@code ActivityCredit} records the financial credit or charge linked to
 * an activity by name and amount, along with its transaction type, creation
 * timestamp, and payment status. This class implements {@link Serializable}
 * to support persistence and data transfer.
 * </p>
 */
public class ActivityCredit implements Serializable {

    /** The name of the activity this credit is associated with. */
    private String activityName;

    /** The monetary amount of this credit entry. */
    private double amount;

    /** The type of transaction (e.g., {@code "CREDIT"} or {@code "DEBIT"}). */
    private String transactionType;

    /** The timestamp indicating when this credit entry was created. */
    private java.sql.Timestamp createdAt;

    /** Indicates whether this credit has been paid out or settled. */
    private boolean paid;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public ActivityCredit() {
    }

    /**
     * Constructs an {@code ActivityCredit} with the essential fields.
     *
     * @param activityName the name of the activity associated with this credit
     * @param amount       the monetary amount of this credit entry
     */
    public ActivityCredit(String activityName, double amount) {
        this.activityName = activityName;
        this.amount = amount;
    }

    /**
     * Returns the name of the activity associated with this credit.
     *
     * @return the activity name
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Sets the name of the activity associated with this credit.
     *
     * @param activityName the activity name to assign
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * Returns the monetary amount of this credit entry.
     *
     * @return the amount as a {@code double}
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the monetary amount of this credit entry.
     *
     * @param amount the amount to assign
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the type of transaction for this credit entry.
     *
     * @return the transaction type (e.g., {@code "CREDIT"} or {@code "DEBIT"})
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the type of transaction for this credit entry.
     *
     * @param transactionType the transaction type to assign (e.g., {@code "CREDIT"} or {@code "DEBIT"})
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Returns the timestamp indicating when this credit entry was created.
     *
     * @return the creation timestamp as a {@link java.sql.Timestamp}
     */
    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp indicating when this credit entry was created.
     *
     * @param createdAt the creation timestamp to assign
     */
    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns whether this credit entry has been paid out or settled.
     *
     * @return {@code true} if this credit has been paid; {@code false} otherwise
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the payment status of this credit entry.
     *
     * @param paid {@code true} if this credit has been paid; {@code false} otherwise
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}