package piazza;

public class Main {
    public static void main(String[] args) {
        PiazzaCtrl controller = new PiazzaCtrl();
        controller.connect();
        controller.login("anders@hotmail.com", "anders");
    }
}
