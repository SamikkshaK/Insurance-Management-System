package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = PropertyUtil.getProperty("db.url");
                String username = PropertyUtil.getProperty("db.username");
                String password = PropertyUtil.getProperty("db.password");

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
        }
        return connection;
    }
}
