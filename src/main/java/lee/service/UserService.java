package lee.service;

import lee.dao.UserDAO;
import lee.model.User;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User register (String username, String password) throws SQLException {
        return userDAO.insert(username,password);
    }

    public User login (String username, String password) throws SQLException {
        return userDAO.select(username,password);
    }
}
