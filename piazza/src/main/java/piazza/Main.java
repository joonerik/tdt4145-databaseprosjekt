package main.java.piazza;

import main.java.piazza.DBConn;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here

        DBConn TestController = new DBConn();
        TestController.connect();

    }
}