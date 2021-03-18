package piazza;

import java.sql.*;

public class User {

    /**
     * The method is static due to not being for one specific user object, but for all. User is logged in if the
     * mail and the password matches an existing user in the database.
     * If the information does not match it returns appropriate feedback.
     *
     * @param mail the mail corresponding to the user logging in.
     * @param password the password of the user logging in.
     * @param conn the connection instance connected to the database.
     * @throws SQLException if something goes wrong running the SQL command.
     */
    public static void login(String mail, String password, Connection conn) throws SQLException {
        String sql = "SELECT Mail FROM User1 WHERE Mail=? AND UserPassword=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, mail);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            System.out.println("Logged in with user: " + rs.getString(1));
        } else {
            System.out.println("Wrong mail or password, could not login");
        }
    }
}
