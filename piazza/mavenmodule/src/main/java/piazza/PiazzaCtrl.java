package piazza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PiazzaCtrl extends DBConn {
    User user = new User();
    Statistics statistics = new Statistics();

    public void login(String mail, String password) {
        try {
            user.login(mail, password, conn);
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    public void createThread(int courseId, int threadNo, int postNo, String postText, String mail,
                             String type, String colorCode, String tag, String foldername) {
        Thread thread = new Thread(courseId, threadNo, tag, colorCode);
        Post post = new Post(courseId, threadNo, postNo, postText, type, mail);
        ThreadInFolder threadInFolder = new ThreadInFolder(courseId, threadNo, foldername);

        try {
            thread.save(conn);
            post.save(conn);
            threadInFolder.save(conn);
            System.out.println("Published the new thread on piazza");
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    public void createPost(int courseId, int threadNo, int postNo, String postText, String type, String mail) {
        Post post = new Post(courseId, threadNo, postNo, postText, type, mail);
        try {
            post.save(conn);
        System.out.println("Published the new " + type + " on piazza");
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    public void searchPosts(String keyword) {
        String sql = "SELECT CourseID, ThreadNo, PostNo FROM Post WHERE Post.PostText LIKE CONCAT('%', ?, '%')";
        try {
            PreparedStatement preparedStatementThread = conn.prepareStatement(sql);
            preparedStatementThread.setString(1, keyword);
            ResultSet rs = preparedStatementThread.executeQuery();
            while (rs.next()) {
                //FIXME formatering her.
                System.out.println(rs.getInt(1) + " - " + rs.getInt(2) + " - " + rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    public void getStatistics() {
        try {
            statistics.getStatistics(conn);
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

}
