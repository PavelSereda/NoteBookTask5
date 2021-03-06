package by.tc.nb.dao.connectionsPool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Pool {
    public static final Pool instance = new Pool();
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(5);

    private Pool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int index = 0; index < pool.remainingCapacity(); index++) {
                pool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook?useSSL=false", "Admin", "1234"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void returnConnection(Connection connection) throws SQLException, InterruptedException {
        if (connection == null) {
            return;
        }
        connection.setAutoCommit(true);
        pool.put(connection);
    }

    public void closePool() {
        for (Connection con : pool) {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }

    public static Pool getInstance() {
        return instance;
    }
}



