package by.tc.nb.command.impl;


import by.tc.nb.bean.AuthorizationRequest;
import by.tc.nb.bean.AuthorizationResponce;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.User;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.UserService;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;

public class Authorization implements Command {
    @Override
    public Response execute(Request request) throws CommandException, IOException, ParseException, ServiceException, ClassNotFoundException {
        AuthorizationRequest req = null;

        if (request instanceof AuthorizationRequest) {
            req = (AuthorizationRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }
        String login = req.getLogin();
        String password = req.getPassword();


        ServiceFactory service = ServiceFactory.getInstance();
        UserService userService = service.getUserService();

        userService.authorization(login, password);

        AuthorizationResponce response = new AuthorizationResponce();

        User currentUser = null;

        currentUser = userService.authorization(login, password);

        if (currentUser == null) {
            response.setErrorStatus(true);
            response.setErrorMessage("Such user doesn't exist");
            return response;
        } else {
            response.setErrorStatus(false);
            response.setErrorMessage("Completed!");
            response.setUser(currentUser);
            return response;
        }
    }
}
