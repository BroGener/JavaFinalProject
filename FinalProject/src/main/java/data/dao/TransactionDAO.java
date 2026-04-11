package data.dao;
import java.util.List;
import transferobjects.AccountTransactionDTO;
/**
 *
 * @author biyababu
 */
public interface TransactionDAO {
 void addTransaction(AccountTransactionDTO transaction) throws Exception;

    List<AccountTransactionDTO> getTransactionsByUser(int userId) throws Exception;   
}
