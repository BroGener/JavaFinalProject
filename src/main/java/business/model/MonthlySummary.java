package business.model;

import java.io.Serializable;

/**
 * Represents a monthly usage and financial summary for a specific user.
 * <p>
 * A {@code MonthlySummary} aggregates a user's scooter activity over a given
 * calendar month, including the number of trips taken, total distance travelled,
 * and the total amount charged. This class implements {@link Serializable} to
 * support persistence and data transfer.
 * </p>
 */
public class MonthlySummary implements Serializable {

    /** The ID of the user this summary belongs to. */
    private int userId;

    /** The calendar year this summary covers (e.g., {@code 2024}). */
    private int year;

    /** The calendar month this summary covers, where {@code 1} = January and {@code 12} = December. */
    private int month;

    /** The total number of trips taken by the user during this month. */
    private int tripCount;

    /** The total distance travelled by the user during this month, in kilometres. */
    private double totalDistanceKm;

    /** The total monetary amount charged to the user during this month. */
    private double totalAmount;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public MonthlySummary() {}

    /**
     * Constructs a fully initialized {@code MonthlySummary} with all fields specified.
     *
     * @param userId          the ID of the user this summary belongs to
     * @param year            the calendar year of the summary (e.g., {@code 2024})
     * @param month           the calendar month of the summary ({@code 1}–{@code 12})
     * @param tripCount       the total number of trips taken during this month
     * @param totalDistanceKm the total distance travelled during this month, in kilometres
     * @param totalAmount     the total monetary amount charged during this month
     */
    public MonthlySummary(int userId, int year, int month, int tripCount,
                          double totalDistanceKm, double totalAmount) {
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.tripCount = tripCount;
        this.totalDistanceKm = totalDistanceKm;
        this.totalAmount = totalAmount;
    }

    /**
     * Returns the ID of the user this summary belongs to.
     *
     * @return the user ID
     */
    public int getUserId() { return userId; }

    /**
     * Sets the ID of the user this summary belongs to.
     *
     * @param userId the user ID to assign
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Returns the calendar year this summary covers.
     *
     * @return the year (e.g., {@code 2024})
     */
    public int getYear() { return year; }

    /**
     * Sets the calendar year this summary covers.
     *
     * @param year the year to assign (e.g., {@code 2024})
     */
    public void setYear(int year) { this.year = year; }

    /**
     * Returns the calendar month this summary covers.
     *
     * @return the month as an integer ({@code 1} = January, {@code 12} = December)
     */
    public int getMonth() { return month; }

    /**
     * Sets the calendar month this summary covers.
     *
     * @param month the month to assign ({@code 1}–{@code 12})
     */
    public void setMonth(int month) { this.month = month; }

    /**
     * Returns the total number of trips taken by the user during this month.
     *
     * @return the trip count
     */
    public int getTripCount() { return tripCount; }

    /**
     * Sets the total number of trips taken by the user during this month.
     *
     * @param tripCount the trip count to assign
     */
    public void setTripCount(int tripCount) { this.tripCount = tripCount; }

    /**
     * Returns the total distance travelled by the user during this month.
     *
     * @return the total distance in kilometres
     */
    public double getTotalDistanceKm() { return totalDistanceKm; }

    /**
     * Sets the total distance travelled by the user during this month.
     *
     * @param totalDistanceKm the total distance in kilometres to assign
     */
    public void setTotalDistanceKm(double totalDistanceKm) { this.totalDistanceKm = totalDistanceKm; }

    /**
     * Returns the total monetary amount charged to the user during this month.
     *
     * @return the total amount as a {@code double}
     */
    public double getTotalAmount() { return totalAmount; }

    /**
     * Sets the total monetary amount charged to the user during this month.
     *
     * @param totalAmount the total amount to assign
     */
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}