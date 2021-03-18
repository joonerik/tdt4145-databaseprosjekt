package piazza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PiazzaCtrl controller = new PiazzaCtrl();
        controller.connect();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a number between 1 and 5 to select usecase: ");
            try {
                int useCaseNumber = Integer.parseInt(sc.next());
                if (useCaseNumber < 1 || useCaseNumber > 5)
                    throw new NumberFormatException();
                switch (useCaseNumber) {
                    case 1 -> controller.login("geu", "du");
                    case 2 -> controller.createThread(1, 5, 1, "text", "anders@hotmail.com", "type", "color", "tag", "exam answers");
                    case 3 -> System.out.println(3);
                    case 4 -> System.out.println(4);
                    case 5 -> System.out.println(5);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 5");
            }
        }
    }
}