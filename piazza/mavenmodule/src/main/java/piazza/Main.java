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
                    case 1 -> controller.login("student@hotmail.com", "studentPassord");
                    case 2 -> controller.createThread(1, 5, 1, "Random text containing WAL, cool.", "student@hotmail.com", "None", "Question", "Exam");
                    case 3 -> controller.createPost(1, 5, 2, "This is an Instructor Answer to post in use case 2", "InstructorAnswer", "teacher@hotmail.com");
                    case 4 -> controller.searchPosts("WAL");
                    case 5 -> controller.getStatistics();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 5");
            }
        }
    }
}
