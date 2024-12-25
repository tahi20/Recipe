package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:postgresql:Recipe";
            String username = "postgres";
            String password = "12345";
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection successful");
            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
                //e.printStackTrace();
                //throw e;
            }
        }
        return connection;
    }
    public static void closeConnection() throws SQLException{
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}