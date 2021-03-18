package piazza;

public class PiazzaCtrl extends DBConn {
    User user = new User();

    public void login(String mail, String password) {
        user.login(mail, password, conn);
    }
}
