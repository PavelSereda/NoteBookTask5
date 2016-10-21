package by.tc.nb.dao.mySQL;


import by.tc.nb.bean.entity.Note;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.dao.NoteBookDao;
import by.tc.nb.dao.connectionsPool.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class MySQLNoteBookDao implements NoteBookDao {
    @Override
    public void addNote(Note note, int id) throws DAOException {
        Connection connection = null;
        try {
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO notes(id_owner, message, date) VALUES("
                        + id + ",'" + note.getNote() + "','" + note.getDateStr() + "');");
            }
        } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void clearNoteBook(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM notes WHERE id_owner=" + id + ";");
            }
        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }


    @Override
    public Set<Note> findNotesByContent(String content, int id) throws DAOException {
        Connection connection = null;
        Set<Note> resultNotes = new HashSet<>();
        try {
            ResultSet resultSet = null;
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT date, message FROM notes WHERE id_owner="
                        + id + " AND message LIKE '%" + content + "%';");
                while (resultSet.next()) {
                    resultNotes.add(new Note(resultSet.getString(1), resultSet.getString(2)));
                }
            }

           /* Iterator<Note> iter = resultNotes.iterator();
            while (iter.hasNext()) {

                System.out.println(iter.next().toString());
            }*/


        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return resultNotes;
    }

    @Override
    public Set<Note> findNotesByDate(String date, int id) throws DAOException {
        Connection connection = null;
        Set<Note> resultList = new HashSet<>();
        try {
            ResultSet resultSet = null;
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT date, message FROM notes WHERE id_owner="
                        + id + " AND date='" + date + "';");
                while (resultSet.next()) {
                    resultList.add(new Note(resultSet.getString(1), resultSet.getString(2)));
                }
            }

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return resultList;
    }

    @Override
    public Set<Note> showNotes(int id) throws DAOException {
        Connection connection = null;
        Set<Note> resultList = new HashSet<>();
        try {
            ResultSet resultSet = null;
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT date, message FROM notes WHERE id_owner='" + id + "';");

                while (resultSet.next()) {
                    resultList.add(new Note(resultSet.getString(1), resultSet.getString(2)));
                }
            }

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return resultList;
    }
}
