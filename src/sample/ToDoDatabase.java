package sample;
import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by RdDvls on 9/28/16.
 */
public class ToDoDatabase {

    int userID;
    public static String getDbUrl() {
        return DB_URL;
    }

    public final static String DB_URL = "jdbc:h2:./main";

    public void init() throws SQLException {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS todos (id IDENTITY, text VARCHAR, is_done BOOLEAN, user_id INT)");
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, username VARCHAR, fullname VARCHAR)");
    }

    public int insertUser(Connection conn, String userName, String fullName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        stmt.setString(1, userName);
        stmt.setString(2, fullName);
        stmt.execute();

        stmt = conn.prepareStatement("SELECT * FROM users where username = ?");
        stmt.setString(1, userName);
        ResultSet results = stmt.executeQuery();
        results.next();
        return results.getInt("id");
    }

    public int selectUser(Connection conn, String userName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt.setString(1, userName);
        ResultSet results = stmt.executeQuery();
        results.next();
        return results.getInt("id");
    }

    public void deleteUser(Connection conn, String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM users where username = ?");
        stmt.setString(1, username);
        stmt.execute();
    }


    public void insertToDo(Connection conn, String text, int userID) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO todos VALUES (NULL, ?, false, ?)");
        stmt.setString(1, text);
        stmt.setInt(2, userID); //query the database to get user id before doing this
        stmt.execute();
    }

    public ArrayList<ToDoItem> selectToDosForUser(Connection conn, int userID) throws SQLException {
        ArrayList<ToDoItem> items = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos " +
                "INNER JOIN users ON todos.user_id = users.id " +
                "WHERE users.id = ?");
        stmt.setInt(1, userID);
        ResultSet results = stmt.executeQuery();

        while (results.next()) {
            int id = results.getInt("id");
            String text = results.getString("text");
            boolean isDone = results.getBoolean("is_done");
            items.add(new ToDoItem(id, text, isDone));
        }
        return items;
    }

    public void deleteToDo(Connection conn, String text) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM todos where text = ?");
        stmt.setString(1, text);
        stmt.execute();
    }


}
