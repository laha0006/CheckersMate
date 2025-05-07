import java.util.*;

public class CheckersMate {

    public static void main(String[] args) {
        Board board = new Board();
       board.useTestBoard();
        Engine engine = new Engine(board,1);
        AI ai = new AI(engine,2);
        DrawBoard draw = new DrawBoard(board);

        int turnsLeft = 100;

        Scanner scanner = new Scanner(System.in);
        String choice;

        while(turnsLeft > 0) {
            System.out.println(draw.printBoard());
            String currentPlayer = engine.getTurn() == 1 ? "Black" : "White";
            System.out.println("It is " + currentPlayer+"'s turn!");

            List<String> moves = engine.getMovesForTurn();
            System.out.println("Available moves:");

            for (String move : moves) {
                System.out.println(move);
            }

            if (engine.getTurn() == 1) {

                System.out.println("Make your move " + currentPlayer + "!");

                choice = scanner.nextLine();

                while (!engine.playerMove(choice)) {
                    System.out.println("Impossible move! Try again!");
                    choice = scanner.nextLine();
                }
            } else {
                String aiMove = ai.getComputerMove();

                if (!engine.playerMove(aiMove)) System.out.println("false");

                System.out.println("Ai moved: " + aiMove);
            }
            turnsLeft--;
        }
//         System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }

}
