package by.tc.nb.service;


import by.tc.nb.bean.entity.Note;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public interface NoteBookService {

    void addNote(String note, String dateStr, int userID) throws ServiceException;

    void clearNote(int userId) throws ServiceException;

    Set<Note> findNotesByContent(String note, int userID) throws ServiceException;

    Set<Note> findNotesByDate(String date, int userID) throws ServiceException;

    Set<Note> showNotes(int userID) throws ServiceException, IOException;

}
