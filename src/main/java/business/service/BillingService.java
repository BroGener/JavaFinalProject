package business.service;

import business.strategy.AccountCalculationStrategy;
import business.strategy.AccountContext;

/**
 * Service interface for billing calculations.
 * <p>
 * Delegates the actual cost computation to a provided
 * {@link AccountCalculationStrategy}, following the <b>Strategy design pattern</b>.
 * </p>
 *
 * @see AccountCalculationStrategy
 * @see AccountContext
 */
public interface BillingService {

    /**
     * Calculates the billing amount using the given strategy and context.
     *
     * @param strategy the calculation strategy to apply (e.g., time-based, distance-based)
     * @param context  the contextual data required for the calculation (e.g., duration, distance)
     * @return the computed billing amount as a {@code double}
     */
    double calculateAmount(AccountCalculationStrategy strategy, AccountContext context);
}