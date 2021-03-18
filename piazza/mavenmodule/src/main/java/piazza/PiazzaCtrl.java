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
        ThreadInFolder threadInFolder = new ThreadInFolder(courseId, threadNo, foldername);

        //Håndter exceptions fra de andre her slik at man kun gir en melding om
        //publisert post dersom det faktsik gikk gjennom.
        thread.save(conn);
        post.save(conn);
        threadInFolder.save(conn);
        System.out.println("Published the new thread on piazza");
    }

    public void reply() {

    }

}
