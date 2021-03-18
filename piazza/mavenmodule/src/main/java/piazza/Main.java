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
                    case 2 -> controller.createThread(1, 5, 1, "text", "anders@hotmail.com", "color", "tag", "exam answers");
                    case 3 -> controller.createPost(1, 5, 2, "Dette er et instructor svar :)", "InstructorAnswer", "anders@hotmail.com");
                    case 4 -> controller.searchPosts("%WAL%");
                    case 5 -> controller.getStatistics();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 5");
            }
        }
    }
}
