package business.strategy;

/**
 * Strategy interface for calculating billing amounts.
 * <p>
 * Part of the <b>Strategy design pattern</b>. Each implementation defines
 * a specific billing algorithm (e.g., time-based, distance-based).
 * </p>
 *
 * @see AccountContext
 */
public interface AccountCalculationStrategy {

    /**
     * Calculates the billing amount based on the provided context.
     *
     * @param context the contextual data required for the calculation
     * @return the computed billing amount as a {@code double}
     */
    double calculate(AccountContext context);
}