package business.service.impl;

import business.factory.UserFactory;
import business.model.User;
import business.service.AuthService;
import data.datasource.DAOFactory;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {
    public Optional<User> login(String email, String password) throws Exception {
        return DAOFactory.getUserDAO().findByEmailAndPassword(email, password);
    }
    public int register(String name, String email, String password, String role) throws Exception {
        User user = UserFactory.createUser(role, name, email, password);
        return DAOFactory.getUserDAO().insert(user);
    }
}
