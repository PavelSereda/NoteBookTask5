package by.tc.nb.view;

import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.bean.entity.User;
import by.tc.nb.controller.Controller;
import by.tc.nb.dao.connectionsPool.Pool;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class View {

    public static void main(String[] args) throws IOException, ParseException, ServiceException, ClassNotFoundException {
        Controller controller = new Controller();
        Scanner in = new Scanner(System.in);

        int loop = 1;
        char reaction;

        while (loop != 0) {
            System.out.println("Press 1- Authorization\n" +
                    "Press 2- Registration\n" +
                    "Press 0- Exit\n");
            reaction = in.nextLine().charAt(0);
            int sessionId = 0;
            switch (reaction) {
                case '0':
                    loop = 0;
                    System.out.println("Exit..");
                    continue;

                case '1':
                    AuthorizationRequest request = new AuthorizationRequest();
                    System.out.println("Login: ");
                    String login = in.nextLine();
                    //request.setLogin();
                    System.out.println("Password: ");
                    String password = in.nextLine();
                    request.setCommandName("AUTHORIZATION");
                    request.setPassword(password);
                    request.setLogin(login);
                    AuthorizationResponce response = (AuthorizationResponce) controller.doRequest(request);
                    if (response.isErrorStatus() == false) {
                        User currentUser = response.getUser();
                        sessionId = currentUser.getId();
                        System.out.println("Welcome , " + currentUser.getLogin() + "!");

                        MenuOptions m = new MenuOptions(controller, sessionId);
                        m.menu();
                    } else {
                        System.out.println("Wrong username or password.");
                        continue;
                    }
                    break;
                case '2':
                    RegistrationRequest registrationRequest = new RegistrationRequest();
                    registrationRequest.setCommandName("REGISTRATION");
                    System.out.println("Login: ");
                    registrationRequest.setLogin(in.nextLine());
                    System.out.println("Password: ");
                    registrationRequest.setPassword(in.nextLine());
                    Response registrationResponce = controller.doRequest(registrationRequest);
                    if (registrationResponce.isErrorStatus() == true) {
                        System.out.println(registrationResponce.getErrorMessage());
                    } else {
                        System.out.println("Completed");
                    }
                    continue;
                default:
                    System.out.println("Wrong command! Please, try again!");
                    continue;
            }
            break;
        }


    }
}
