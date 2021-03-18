package piazza;

import java.sql.*;

public class User {


    public void login(String mail, String password, Connection conn) {
        String sql = "SELECT Mail FROM User1 WHERE Mail=? AND UserPassword=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("Logged in with user: " + rs.getString(1));
            } else {
                System.out.println("Wrong mail or password, could not login");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in the database: " + e);
        }
    }
}
