package by.tc.nb.command.impl;


import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.ShowNotesRequest;
import by.tc.nb.bean.ShowNotesResponse;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.util.Set;


public class ShowNotes implements Command {
    @Override
    public Response execute(Request request) throws CommandException, IOException, ServiceException {
        ShowNotesRequest req = null;

        if (request instanceof ShowNotesRequest) {
            req = (ShowNotesRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        ShowNotesResponse response = new ShowNotesResponse();
        Set<Note> result = null;

        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();


        try {
            result = nbService.showNotes(req.getUserID());
        } catch (ServiceException e) {
            throw new CommandException();
        }

        response.setErrorStatus(false);
        response.setNotes(result);
        response.setResultMessage("All OK!");
        return response;
    }
}
