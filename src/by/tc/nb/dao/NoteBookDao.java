package by.tc.nb.dao;


import by.tc.nb.bean.entity.Note;
import by.tc.nb.dao.exception.DAOException;

import java.util.Set;

public interface NoteBookDao {

    void addNote(Note note, int id) throws DAOException;
    void clearNoteBook(int id) throws DAOException;
    Set<Note> findNotesByContent(String content, int id) throws DAOException;
    Set<Note> findNotesByDate(String date, int id) throws DAOException;
    Set<Note> showNotes(int id) throws DAOException;
}
