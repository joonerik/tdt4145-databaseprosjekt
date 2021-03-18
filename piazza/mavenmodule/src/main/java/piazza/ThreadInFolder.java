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

    /**
     * Saves the current threadInFolder object to the database. This links the thread to a folder, such that one
     * can see which folders a thread lies in.
     *
     * @param conn the connection instance connected to the database.
     * @throws SQLException if something goes wrong running the SQL command.
     */
    @Override
    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO ThreadInFolder VALUES (?, ?, ?)";
        PreparedStatement preparedStatementThread = conn.prepareStatement(sql);
        preparedStatementThread.setInt(1, courseId);
        preparedStatementThread.setInt(2, threadNo);
        preparedStatementThread.setString(3, foldername);
        preparedStatementThread.executeUpdate();
    }
}
