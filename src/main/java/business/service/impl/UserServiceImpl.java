package business.service.impl;

import business.model.User;
import business.service.UserService;
import data.datasource.DAOFactory;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    public Optional<User> getUserById(int userId) throws Exception { return DAOFactory.getUserDAO().findById(userId); }
    public Optional<User> getUserByEmail(String email) throws Exception { return DAOFactory.getUserDAO().findByEmail(email); }
}
