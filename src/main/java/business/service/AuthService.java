package business.service;

import business.model.User;
import java.util.Optional;

public interface AuthService {
    Optional<User> login(String email, String password) throws Exception;
    int register(String name, String email, String password, String role) throws Exception;
}