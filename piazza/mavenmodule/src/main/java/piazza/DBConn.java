package piazza;

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {
    protected Connection conn;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Properties for user and password.
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "database123");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database",p);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect, with error: ", e);
        }
    }
}