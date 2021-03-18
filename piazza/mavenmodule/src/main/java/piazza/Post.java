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

    /**
     * Constructor for Post.
     *
     * @param courseId the course id where the thread belongs.
     * @param threadNo the threadNumber of the thread.
     * @param postNo the postNumber of the original post.
     * @param postText the text of the original post.
     * @param postType the type of post, e.g., OriginalPost, InstructorAnswer, etc.
     * @param mail the creator of the post.
     */
    public Post(int courseId, int threadNo, int postNo, String postText, String postType, String mail) {
        this.courseId = courseId;
        this.threadNo = threadNo;
        this.postNo = postNo;
        this.postText = postText;
        this.postType = postType;
        this.mail = mail;
    }

    /**
     * Saves the post object to the database. The post is then linked to the thread which it belongs to, and is marked
     * with a type corresponding to the type of post it is.
     *
     * @param conn the connection instance connected to the database.
     * @throws SQLException if something goes wrong running the SQL command.
     */
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

    /**
     * The method is static due to not being for one specific post object, but for all. It searches through all posts
     * in the database, and retrieves the id to each post where the post contains the keyword in its postText.
     *
     * @param keyword the keyword to search for in posts.
     * @param conn the connection instance connected to the database.
     */
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
