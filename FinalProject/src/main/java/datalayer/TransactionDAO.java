/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
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
