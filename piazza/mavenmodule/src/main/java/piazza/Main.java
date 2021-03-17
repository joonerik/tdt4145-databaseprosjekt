package piazza;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DBConn controller = new DBConn();
        controller.connect();
    }
}
