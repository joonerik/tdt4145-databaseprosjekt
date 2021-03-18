package piazza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Post extends ActiveDomainObject {
    private int courseId;
    private int threadNo;
    private int postNo;
    private String postText;
    private String postType;
    private String mail;

    public Post(int courseId, int threadNo, int postNo, String postText, String postType, String mail) {
        this.courseId = courseId;
        this.threadNo = threadNo;
        this.postNo = postNo;
        this.postText = postText;
        this.postType = postType;
        this.mail = mail;
    }


    @Override
    public void initialize(Connection conn) {

    }

    @Override
    public void refresh(Connection conn) {

    }

    @Override
    public void save(Connection conn) throws SQLException {
        String createPostSql = "INSERT INTO Post VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatementThread = conn.prepareStatement(createPostSql);
        preparedStatementThread.setInt(1, courseId);
        preparedStatementThread.setInt(2, threadNo);
        preparedStatementThread.setInt(3, postNo);
        preparedStatementThread.setString(4, postText);
        preparedStatementThread.setString(5, mail);
        preparedStatementThread.setString(6, postType);
        preparedStatementThread.executeUpdate();
    }
}
