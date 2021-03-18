package piazza;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PiazzaCtrl extends DBConn {
    User user = new User();

    public void login(String mail, String password) {
        user.login(mail, password, conn);
    }

    public void createThread(int courseId, int threadNo, int postNo, String postText, String mail,
                             String type, String colorCode, String tag, String foldername) {
        Thread thread = new Thread(courseId, threadNo, tag, colorCode);
        Post post = new Post(courseId, threadNo, postNo, postText, type, mail);

        thread.save(conn);
        post.save(conn);

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
        System.out.println("Published the new thread on piazza");
    }

}
