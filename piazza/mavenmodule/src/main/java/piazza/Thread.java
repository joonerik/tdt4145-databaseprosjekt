package piazza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Thread extends ActiveDomainObject {
    private final int courseId;
    private final int threadNo;
    private final String tag;
    private final String colorCode;

    public Thread(int courseId, int threadNo, String tag, String colorCode) {
        this.courseId = courseId;
        this.threadNo = threadNo;
        this.tag = tag;
        this.colorCode = colorCode;
    }

    /**
     * Saves the thread object to the database. This will create a thread such that it is possible to link other posts
     * and followup discussions to it later on.
     *
     * @param conn the connection instance connected to the database.
     * @throws SQLException if something goes wrong running the SQL command.
     */
    @Override
    public void save(Connection conn) throws SQLException {
        String createThreadSql = "INSERT INTO Thread VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatementThread = conn.prepareStatement(createThreadSql);
        preparedStatementThread.setInt(1, courseId);
        preparedStatementThread.setInt(2, threadNo);
        preparedStatementThread.setString(3, tag);
        preparedStatementThread.setString(4, colorCode);
        preparedStatementThread.executeUpdate();
    }
}
