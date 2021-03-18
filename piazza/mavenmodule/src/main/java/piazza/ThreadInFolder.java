package piazza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThreadInFolder extends ActiveDomainObject {
    private final int courseId;
    private final int threadNo;
    private final String foldername;

    public ThreadInFolder(int courseId, int threadNo, String foldername) {
        this.courseId = courseId;
        this.threadNo = threadNo;
        this.foldername = foldername;
    }

    @Override
    public void save(Connection conn) {
        try {
            String sql = "INSERT INTO ThreadInFolder VALUES (?, ?, ?)";
            PreparedStatement preparedStatementThread = conn.prepareStatement(sql);
            preparedStatementThread.setInt(1, courseId);
            preparedStatementThread.setInt(2, threadNo);
            preparedStatementThread.setString(3, foldername);
            preparedStatementThread.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occured in the database " + e);
        }
    }
}
