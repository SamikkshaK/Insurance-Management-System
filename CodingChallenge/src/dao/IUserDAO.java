package dao;

import entity.User;

public interface IUserDAO {
    boolean registerUser(User user);
    User loginUser(String username, String password);
}
