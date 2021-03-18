package piazza;

import java.sql.SQLException;

public class PiazzaCtrl extends DBConn {

    /**
     * Tries to log in a user, delegates the task to the User class where a static login method is implemented.
     *
     * @param mail the mail that the user provided.
     * @param password the password that the user provided.
     */
    public void login(String mail, String password) {
        try {
            User.login(mail, password, conn);
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    /**
     * Creates a new thread, delegates the task to the Thread class, by creating an object with the provided arguments
     * and calling the save method on the object.
     *
     * @param courseId the course id where the thread belongs.
     * @param threadNo the threadNumber of the thread.
     * @param postNo the postNumber of the original post.
     * @param postText the text of the original post.
     * @param mail the mail of the user publishing the thread.
     * @param colorCode the colorCode describing the thread.
     * @param tag the tag of the thread, e.g., question, announcement
     * @param foldername the name of the folder where the thread is to be placed.
     */
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

    /**
     * Creates a new post, delegates the task to the Post class, by creating a post object with the provided arguments,
     * and calling the method save on the object.
     *
     * @param courseId the courseId where the post belongs.
     * @param threadNo the threadNumber corresponding to which thread the post is in.
     * @param postNo the postNumber for the post.
     * @param postText the text inside the post.
     * @param type the type of the post.
     * @param mail the mail of the user publishing the post.
     */
    public void createPost(int courseId, int threadNo, int postNo, String postText, String type, String mail) {
        Post post = new Post(courseId, threadNo, postNo, postText, type, mail);
        try {
            post.save(conn);
            System.out.println("Published the new " + type + " on piazza");
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }

    /**
     * Searches for the keyword provided, and retrieves all the id's for posts containing
     * the keyword in its postText.
     *
     * @param keyword the keyword to be searched for.
     */
    public void searchPosts(String keyword) {
        Post.searchPost(keyword, conn);
    }

    /**
     * Retrieves all the statistics about how many posts each users have created and read.
     *
     */
    public void getStatistics() {
        try {
            Statistics.getStatistics(conn);
        } catch (SQLException e) {
            System.out.println("An error occurred in the database " + e);
        }
    }
}
