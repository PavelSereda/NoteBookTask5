package by.tc.nb.service.impl;


import by.tc.nb.bean.entity.Note;
import by.tc.nb.dao.DaoFactory;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.exception.ServiceException;

import java.io.*;
import java.util.Set;

public class NoteBookServiceImpl implements NoteBookService {


    @Override
    public void addNote(String note,String dateStr, int userID) throws ServiceException {
        if(note == null || note.equals("") || userID < 0) {
            throw new ServiceException("Invalid parameters");
        }
        try {
            DaoFactory.getInstance().getNoteBookDAO().addNote(new Note(note,dateStr), userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void clearNote(int userID) throws ServiceException {
        if(userID < 0) {
            throw new ServiceException("Invalid parameters");
        }

        try {
            DaoFactory.getInstance().getNoteBookDAO().clearNoteBook(userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Set<Note> findNotesByContent(String note, int userID) throws ServiceException {
        if(note == null || note.equals("") || userID < 0) {
            throw new ServiceException("Invalid parameters");
        }
        try {
            return DaoFactory.getInstance().getNoteBookDAO().findNotesByContent(note, userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Set<Note> findNotesByDate(String date, int userID) throws ServiceException {
        if(date == null || userID < 0) {
            throw new ServiceException("Invalid parameters");
        }
        try {
            return DaoFactory.getInstance().getNoteBookDAO().findNotesByDate(date, userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }



    @Override
    public Set<Note> showNotes(int userID) throws ServiceException, IOException {
        if(userID < 0) {
            throw new ServiceException("Invalid parameters");
        }
        try {
            return DaoFactory.getInstance().getNoteBookDAO().showNotes(userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
