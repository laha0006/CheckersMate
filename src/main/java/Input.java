import java.util.List;
import java.util.Scanner;

public class Input {

    public static int selectColor(Scanner scanner, Engine engine) {
        System.out.println(
            """
            Select Player color:
            1. Black (goes first)
            2. White (goes second)""");

        int choice = 0;

        while (choice <= 0 || choice > 3) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
            if (choice <= 0 || choice > 3) {
                System.out.println("Please enter 1 or 2:");
            }
        }
        return choice == 1 ? engine.black : engine.white;
    }

    public static long setMoveTimer(Scanner scanner) {
        System.out.println("Set move timer for AI.");
        int seconds = 0;

        while (seconds <= 0 || seconds >= 60) {
            try {
                System.out.println("Enter a number between 1-60:");
                seconds = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        return seconds * 1000L;
    }

    public static int preview(Scanner scanner, List<String> moves) {
        int choice = -1;

        while (choice < 1 || choice > moves.size()) {
            try {
                System.out.println("Choose a number between 1 and " + moves.size() + ":");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        return choice - 1;
    }

    public static boolean confirm(Scanner scanner, int choiceNr) {
        int choice = -1;
        choiceNr++;

        while (choice != choiceNr && choice != 0) {
            try {
                System.out.println(choiceNr + ": Confirm choice\n0: Cancel");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        if (choice == 0) {
            System.out.println("false");
            return false;
        }
        System.out.println("true");
        return true;
    }
}
