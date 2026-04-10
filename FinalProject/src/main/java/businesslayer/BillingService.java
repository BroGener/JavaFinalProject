/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;
import datalayer.TransactionDAO;
import transferobjects.AccountTransactionDTO;
import java.sql.Timestamp;
import java.util.List;
import patterns.strategy.AccountCalculationStrategy;
import patterns.strategy.AccountContext;

/**
 *
 * @author biyababu
 */
public class BillingService {
  private TransactionDAO transactionDAO;

    public BillingService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

   
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

  
    public double calculateAmount(AccountCalculationStrategy strategy,List<AccountTransactionDTO> transactions ) {

        AccountContext context = new AccountContext(strategy);

        return context.executeStrategy(transactions);
    }

    
    public List<AccountTransactionDTO> getUserTransactions(int userId) throws Exception {
        return transactionDAO.getTransactionsByUser(userId);
    }  
}
