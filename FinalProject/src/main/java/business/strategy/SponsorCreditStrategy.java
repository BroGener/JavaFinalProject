package business.strategy;

public class SponsorCreditStrategy implements AccountCalculationStrategy {
    @Override
    public double calculate(AccountContext context) {
        return 5.00;
    }
}