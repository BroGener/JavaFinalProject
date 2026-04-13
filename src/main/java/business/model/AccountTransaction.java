package business.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a financial transaction associated with a user account.
 * <p>
 * Each {@code AccountTransaction} records a single monetary event tied to a
 * specific user and activity, including the amount, type of transaction, and
 * the date it occurred. This class implements {@link Serializable} to support
 * persistence and data transfer.
 * </p>
 */
public class AccountTransaction implements Serializable {

    /** The unique identifier for this transaction. */
    private Integer transactionId;

    /** The ID of the user associated with this transaction. */
    private Integer userId;

    /** The name of the activity that triggered this transaction (e.g., a ride or top-up). */
    private String activityName;

    /** The monetary amount involved in this transaction. */
    private double amount;

    /** The type of transaction (e.g., {@code "CREDIT"} or {@code "DEBIT"}). */
    private String transactionType;

    /** The date on which this transaction occurred. */
    private LocalDate transactionDate;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public AccountTransaction() {}

    /**
     * Constructs a fully initialized {@code AccountTransaction} with all fields specified.
     *
     * @param transactionId   the unique identifier for this transaction, or {@code null} if not yet persisted
     * @param userId          the ID of the user associated with this transaction
     * @param activityName    the name of the activity that triggered this transaction
     * @param amount          the monetary amount of the transaction
     * @param transactionType the type of transaction (e.g., {@code "CREDIT"} or {@code "DEBIT"})
     * @param transactionDate the date on which the transaction occurred
     */
    public AccountTransaction(Integer transactionId, Integer userId, String activityName,
                              double amount, String transactionType, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.activityName = activityName;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    /**
     * Returns the unique identifier of this transaction.
     *
     * @return the transaction ID, or {@code null} if not yet persisted
     */
    public Integer getTransactionId() { return transactionId; }

    /**
     * Sets the unique identifier of this transaction.
     *
     * @param transactionId the transaction ID to assign
     */
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }

    /**
     * Returns the ID of the user associated with this transaction.
     *
     * @return the user ID
     */
    public Integer getUserId() { return userId; }

    /**
     * Sets the ID of the user associated with this transaction.
     *
     * @param userId the user ID to assign
     */
    public void setUserId(Integer userId) { this.userId = userId; }

    /**
     * Returns the name of the activity that triggered this transaction.
     *
     * @return the activity name (e.g., a ride session or account top-up)
     */
    public String getActivityName() { return activityName; }

    /**
     * Sets the name of the activity associated with this transaction.
     *
     * @param activityName the activity name to assign
     */
    public void setActivityName(String activityName) { this.activityName = activityName; }

    /**
     * Returns the monetary amount of this transaction.
     *
     * @return the transaction amount as a {@code double}
     */
    public double getAmount() { return amount; }

    /**
     * Sets the monetary amount of this transaction.
     *
     * @param amount the amount to assign
     */
    public void setAmount(double amount) { this.amount = amount; }

    /**
     * Returns the type of this transaction.
     *
     * @return the transaction type (e.g., {@code "CREDIT"} or {@code "DEBIT"})
     */
    public String getTransactionType() { return transactionType; }

    /**
     * Sets the type of this transaction.
     *
     * @param transactionType the transaction type to assign (e.g., {@code "CREDIT"} or {@code "DEBIT"})
     */
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    /**
     * Returns the date on which this transaction occurred.
     *
     * @return the transaction date as a {@link LocalDate}
     */
    public LocalDate getTransactionDate() { return transactionDate; }

    /**
     * Sets the date on which this transaction occurred.
     *
     * @param transactionDate the transaction date to assign
     */
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
}