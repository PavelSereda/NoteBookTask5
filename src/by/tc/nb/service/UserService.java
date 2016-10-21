package by.tc.nb.service;


import by.tc.nb.bean.entity.User;
import by.tc.nb.service.exception.ServiceException;

public interface UserService {
    User authorization(String login, String password) throws ServiceException;

    boolean registration(String login, String password) throws ServiceException;
}
