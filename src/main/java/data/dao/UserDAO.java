package data.dao;

import business.model.User;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User, Integer> {
    Optional<User> findByEmail(String email) throws Exception;
    Optional<User> findByEmailAndPassword(String email, String password) throws Exception;
}