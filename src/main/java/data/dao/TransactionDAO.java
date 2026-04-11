package data.dao;

import business.model.AccountTransaction;
import java.util.List;

public interface TransactionDAO extends GenericDAO<AccountTransaction, Integer> {
    List<AccountTransaction> findByUserId(int userId) throws Exception;
    List<AccountTransaction> findByUserIdAndMonth(int userId, int year, int month) throws Exception;
}