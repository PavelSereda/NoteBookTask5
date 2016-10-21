package by.tc.nb.dao;


import by.tc.nb.dao.mySQL.MySQLNoteBookDao;
import by.tc.nb.dao.mySQL.MySQLUserDao;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private final NoteBookDao noteBookDAO = new MySQLNoteBookDao();
    private final UserDao userDAO = new MySQLUserDao();


    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public NoteBookDao getNoteBookDAO() {
        return noteBookDAO;
    }

    public UserDao getUserDAO() {
        return userDAO;
    }

}
