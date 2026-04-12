package data.daoimpl;

import business.model.User;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements data.dao.UserDAO {
    public Integer insert(User entity) {
        entity.setUserId(MockDataStore.nextUserId());
        MockDataStore.USERS.add(entity);
        return entity.getUserId();
    }
    public boolean update(User entity) {
        return true;
    }
    public boolean deleteById(Integer id) { return false; }
    public Optional<User> findById(Integer id) {
        for (User user : MockDataStore.USERS) {
            if (user.getUserId().equals(id)) return Optional.of(user);
        }
        return Optional.empty();
    }
    public List<User> findAll() { return new ArrayList<User>(MockDataStore.USERS); }
    public Optional<User> findByEmail(String email) { return MockDataStore.findUserByEmail(email); }
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> user = MockDataStore.findUserByEmail(email);
        return user.isPresent() && password.equals(user.get().getPassword()) ? user : Optional.<User>empty();
    }
}
