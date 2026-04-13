package business.strategy;

public class SponsorCreditStrategy implements AccountCalculationStrategy {
    @Override
    public double calculate(AccountContext context) {
        // 30% of user debit
        return Math.round(context.getMinutesAwayFromStation() * 0.30 ) ;
    }
}