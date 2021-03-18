package piazza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post extends ActiveDomainObject {
    private final int courseId;
    private final int threadNo;
    private final int postNo;
    private final String postText;
    private final String postType;
    private final String mail;

    public Post(int courseId, int threadNo, int postNo, String postText, String postType, String mail) {
        this.courseId = courseId;
        this.threadNo = threadNo;
        this.postNo = postNo;
        this.postText = postText;
        this.postType = postType;
        this.mail = mail;
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

    public static void searchPost(String keyword, Connection conn) {
        String sql = "SELECT CourseID, ThreadNo, PostNo FROM Post WHERE Post.PostText LIKE CONCAT('%', ?, '%')";
        try {
            PreparedStatement preparedStatementThread = conn.prepareStatement(sql);
            preparedStatementThread.setString(1, keyword);
            ResultSet rs = preparedStatementThread.executeQuery();
            System.out.format("%10s%10s%10s", "CourseID", "ThreadNo", "PostNo \n");
            while (rs.next()) {
                System.out.format("%9d%9d%9s", rs.getInt(1), rs.getInt(2), rs.getInt(3) + "\n");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }
}
