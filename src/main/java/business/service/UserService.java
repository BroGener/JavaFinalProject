package business.service;

import business.model.User;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(int userId) throws Exception;
    Optional<User> getUserByEmail(String email) throws Exception;

}