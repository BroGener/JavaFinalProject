package business.service.impl;

import business.service.BillingService;
import business.strategy.AccountCalculationStrategy;
import business.strategy.AccountContext;

public class BillingServiceImpl implements BillingService {
    public double calculateAmount(AccountCalculationStrategy strategy, AccountContext context) {
        return strategy.calculate(context);
    }
}
