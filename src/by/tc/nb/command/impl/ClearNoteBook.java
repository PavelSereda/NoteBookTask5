package by.tc.nb.command.impl;


import by.tc.nb.bean.ClearNoteBookRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;

public class ClearNoteBook implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        ClearNoteBookRequest req = null;

        if(request instanceof ClearNoteBookRequest){
            req = (ClearNoteBookRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        Response response = new Response();

        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            nbService.clearNote(req.getUserID());
        } catch (ServiceException e) {
            response.setErrorStatus(true);
            response.setErrorMessage(e.getMessage());
            return response;
        }

        response.setErrorStatus(false);
        response.setResultMessage("Completed!");

        return response;
    }
}
