package piazza;

import java.sql.SQLException;

public class PiazzaCtrl extends DBConn {

    public void login(String mail, String password) {
        try {
            User.login(mail, password, conn);
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    public void createThread(int courseId, int threadNo, int postNo, String postText, String mail,
                             String colorCode, String tag, String foldername) {

        Thread thread = new Thread(courseId, threadNo, tag, colorCode);
        ThreadInFolder threadInFolder = new ThreadInFolder(courseId, threadNo, foldername);

        try {
            thread.save(conn);
            System.out.println("Published the new thread on piazza");
            createPost(courseId, threadNo, postNo, postText, "OriginalPost", mail);
            threadInFolder.save(conn);
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
        Post.searchPost(keyword, conn);
    }

    public void getStatistics() {
        try {
            Statistics.getStatistics(conn);
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }
}
