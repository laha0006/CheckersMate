import java.util.List;
import java.util.Scanner;

public class Input {

    public static int preview(List<String> moves) {
        Scanner scanner = new Scanner(System.in);
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

    public static boolean confirm(int choiceNr) {
        Scanner scanner = new Scanner(System.in);
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
