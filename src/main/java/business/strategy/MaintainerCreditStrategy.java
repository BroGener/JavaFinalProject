package business.strategy;

public class MaintainerCreditStrategy implements AccountCalculationStrategy {
    @Override
    public double calculate(AccountContext context) {
        return context.getScootersReturned() * 3.50;
    }
}