package by.tc.nb.dao;

import by.tc.nb.bean.entity.User;
import by.tc.nb.dao.exception.DAOException;


public interface UserDao {
    User authorization(String login, String password) throws DAOException;
    boolean registration(String login, String password) throws DAOException;
}
