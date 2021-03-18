package piazza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Thread extends ActiveDomainObject {
    private int courseId;
    private int threadNo;
    private String tag;
    private String colorCode;

    public Thread(int courseId, int threadNo, String tag, String colorCode) {
        this.courseId = courseId;
        this.threadNo = threadNo;
        this.tag = tag;
        this.colorCode = colorCode;
    }

    @Override
    public void initialize(Connection conn) {
    }

    @Override
    public void refresh(Connection conn) {
    }

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
