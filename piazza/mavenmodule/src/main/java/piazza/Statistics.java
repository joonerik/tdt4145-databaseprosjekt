package piazza;

import java.sql.*;

public class Statistics {

    public static void getStatistics(Connection conn) throws SQLException {
        String sql = "SELECT User1.mail AS username, COUNT(HasRead.mail) AS read1, created.createdNo " +
                "FROM User1 LEFT OUTER JOIN HasRead USING(mail) " +
                "LEFT OUTER JOIN (SELECT User1.mail, COUNT(Post.mail) AS createdNo " +
                "FROM User1 LEFT OUTER JOIN Post USING(mail) " +
                "GROUP BY User1.mail) " +
                "AS created USING(mail) " +
                "GROUP BY username, created.createdNo " +
                "ORDER BY read1 DESC";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        System.out.format("%32s%10s%16s", "User", "Has read", "Has created \n");
        while (rs.next()) {
            System.out.format("%32s%10d%16s", rs.getString(1),
                    rs.getInt(2), rs.getInt(3) + "\n");
        }
    }
}
