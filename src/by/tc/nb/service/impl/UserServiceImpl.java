package by.tc.nb.service.impl;


import by.tc.nb.bean.entity.User;
import by.tc.nb.dao.DaoFactory;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.UserService;
import by.tc.nb.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public User authorization(String login, String password) throws ServiceException {
        if (login == null || login.equals("") || password == null || password.equals("")) {
            // throw new ServiceException("Invalid parameters");
        }
        try {
            return DaoFactory.getInstance().getUserDAO().authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        if (login == null || login.equals("") || password == null || password.equals("")) {
            throw new ServiceException("Invalid parameters");
        }

        try {
            return DaoFactory.getInstance().getUserDAO().registration(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }
}
