/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.model;

/**
 *
 * @author biyababu
 */
import data.dao.TransactionDAO;
import transferobjects.AccountTransactionDTO;
import java.sql.Timestamp;
import java.util.List;
import business.strategy.AccountCalculationStrategy;
import business.strategy.AccountContext;

/**
 *
 * @author biyababu
 */
/*
*This class handles business logic related to account billing operations.
*/
public class Billing {
  private TransactionDAO transactionDAO;

    /**
     * Constructor to initialize TransactionDAO
     * 
     * @param transactionDAO DAO used for database operations
     */
    public Billing(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

   /**
    * Processes a transaction by validating and saving it to the database
    * 
    * @param transaction AccountTransactionDTO object
    * @throws Exception if validation fails or database error occurs
    */
    public void processTransaction(AccountTransactionDTO transaction) throws Exception {

       if (transaction == null) {
            throw new Exception("Transaction cannot be null value");
        }
        if (transaction.getAmount() <= 0) {
            throw new Exception("Amount must be greater than 0");
        }

        if (transaction.getCreatedAt() == null) {
            transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }

        transactionDAO.addTransaction(transaction);
    }

   /**
    * Calculates billing amount using a strategy
    * 
    * @param strategy calculation strategy
    * @param distanceKm distance traveled in kilometers
    * @param minutesAway time away from station
    * @param scootersReturned  number of scooters returned
    * @return calculated billing amount
    */
    public double calculateAmount(AccountCalculationStrategy strategy,
                                  double distanceKm,
                                  double minutesAway,
                                  int scootersReturned) {

        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }

    
    AccountContext context =
                new AccountContext(distanceKm, minutesAway, scootersReturned);
     return strategy.calculate(context);
}

}