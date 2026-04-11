package business.service;

import business.strategy.AccountCalculationStrategy;
import business.strategy.AccountContext;

public interface BillingService {
    double calculateAmount(AccountCalculationStrategy strategy, AccountContext context);
}