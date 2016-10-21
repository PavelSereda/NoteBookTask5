package by.tc.nb.dao.mySQL;

import by.tc.nb.bean.entity.User;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.dao.UserDao;
import by.tc.nb.dao.connectionsPool.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUserDao implements UserDao {
    @Override
    public User authorization(String login, String password) throws DAOException {
        User user = null;
        Connection connection = null;
        try {
            connection = Pool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT id, login FROM users WHERE login='"
                        + login + "' AND password='" + password + "';");

                if(resultSet.next()) {
                    user = new User(resultSet.getInt(1), resultSet.getString(2));
                }

            }
        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public boolean registration(String login, String password) throws DAOException {
        Connection connection = null;
        try {
            connection = Pool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                int result = statement.executeUpdate("INSERT INTO users (login,password) VALUES('"
                        + login +"', '" + password +"');");
                return (result != 0)? true : false;
            }
        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }
}
