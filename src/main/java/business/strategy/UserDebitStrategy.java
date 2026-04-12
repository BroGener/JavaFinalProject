package business.strategy;

public class UserDebitStrategy implements AccountCalculationStrategy {
    @Override
    public double calculate(AccountContext context) {
        return context.getDistanceKm() * 1.25 + context.getMinutesAwayFromStation() * 0.10;
    }
}