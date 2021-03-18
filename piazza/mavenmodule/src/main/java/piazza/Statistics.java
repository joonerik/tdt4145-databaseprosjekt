package piazza;

import java.sql.*;

public class Statistics {

    public void getStatistics(Connection conn) {
        String sql = "SELECT User1.mail AS username, COUNT(HasRead.mail) AS read1, created.createdNo " +
        "FROM User1 LEFT OUTER JOIN HasRead USING(mail) " +
        "LEFT OUTER JOIN (SELECT User1.mail, COUNT(Post.mail) AS createdNo " +
                "FROM User1 LEFT OUTER JOIN Post USING(mail) " +
                "GROUP BY User1.mail) " +
        "AS created USING(mail) " +
        "GROUP BY username, created.createdNo " +
        "ORDER BY read1 DESC";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String row = "User: " + rs.getString(1) + ", has read: " + rs.getInt(2)
                        + ", has created: " + rs.getInt(3);
                System.out.println(row);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in the database: " + e);
        }
    }
}
