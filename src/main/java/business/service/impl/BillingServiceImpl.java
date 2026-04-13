package business.service.impl;

import business.service.BillingService;
import business.strategy.AccountCalculationStrategy;
import business.strategy.AccountContext;

/**
 * Implementation of {@link BillingService} that delegates amount calculation
 * to the provided {@link AccountCalculationStrategy}.
 */
public class BillingServiceImpl implements BillingService {

    /**
     * Calculates the billing amount by invoking the given strategy with the provided context.
     *
     * @param strategy the calculation strategy to apply (e.g., time-based, distance-based)
     * @param context  the contextual data required for the calculation
     * @return the computed billing amount as a {@code double}
     */
    public double calculateAmount(AccountCalculationStrategy strategy, AccountContext context) {
        return strategy.calculate(context);
    }
}