import java.util.*;

public class CheckersMate {

    public static void main(String[] args) {
        Board board = new Board();
        board.useTestBoard();
        Engine engine = new Engine(board, 1);
        AI ai = new AI(engine, 2);
        DrawBoard draw = new DrawBoard(board);

        int turnsLeft = 100;

        while (turnsLeft > 0) {
            int choiceNr;
            boolean choiceConfirmed = false;

            List<String> moves = engine.getMovesForTurn();

            
            String currentPlayer = engine.getTurn() == 1 ? "Black" : "White";
            System.out.println("It is " + currentPlayer + "'s turn!");

            

            if (engine.getTurn() == 1) {

                System.out.println("Make your move " + currentPlayer + "!\n");

                while (!choiceConfirmed) {
                    System.out.println("Available moves:");
                    for (int i = 0; i < moves.size(); i++) {
                        System.out.println(i + 1 + ": " + moves.get(i));
                    }
                    System.out.println(draw.printBoard());

                    choiceNr = Input.preview(moves);
                    System.out.println(draw.printPreviewBoard(moves.get(choiceNr)));
                    choiceConfirmed = Input.confirm(choiceNr);

                    if (choiceConfirmed) {
                        engine.playerMove(moves.get(choiceNr));
                    }

                }

            } else {
                String aiMove = ai.getComputerMove();

                if (!engine.playerMove(aiMove))
                    System.out.println("false");

                System.out.println("Ai moved: " + aiMove);
            }
            turnsLeft--;
        }
        // System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }

}
