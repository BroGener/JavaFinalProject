package data.daoimpl;

import business.model.AccountTransaction;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDAO implements data.dao.TransactionDAO {
    public Integer insert(AccountTransaction entity) { return entity.getTransactionId(); }
    public boolean update(AccountTransaction entity) { return true; }
    public boolean deleteById(Integer id) { return false; }
    public Optional<AccountTransaction> findById(Integer id) {
        for (AccountTransaction transaction : MockDataStore.TRANSACTIONS) {
            if (transaction.getTransactionId().equals(id)) return Optional.of(transaction);
        }
        return Optional.empty();
    }
    public List<AccountTransaction> findAll() { return new ArrayList<AccountTransaction>(MockDataStore.TRANSACTIONS); }
    public List<AccountTransaction> findByUserId(int userId) {
        List<AccountTransaction> list = new ArrayList<AccountTransaction>();
        for (AccountTransaction transaction : MockDataStore.TRANSACTIONS) {
            if (transaction.getUserId() != null && transaction.getUserId() == userId) list.add(transaction);
        }
        return list;
    }
    public List<AccountTransaction> findByUserIdAndMonth(int userId, int year, int month) { return findByUserId(userId); }
}
