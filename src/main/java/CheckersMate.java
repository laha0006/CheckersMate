import java.util.*;

public class CheckersMate {

    public static void main(String[] args) {
        Board board = new Board();

        Engine engine = new Engine(board, 1);
        AI ai = new AI(engine, 2);
        DrawBoard draw = new DrawBoard(board);

        Scanner scanner = new Scanner(System.in);
        int player = Input.selectColor(scanner,engine);
        ai.setMaxTime(Input.setMoveTimer(scanner));

        if(player == engine.white){
            System.out.println(draw.printBoard());
        }

        int turnsLeft = 100;

        while (turnsLeft > 0) {

            int choiceNr;
            boolean choiceConfirmed = false;


            List<String> moves = engine.getMovesForTurn();
            if(moves.isEmpty()) {
                if(engine.getTurn() == player) {
                    System.out.println("White Wins");
                } else {
                    System.out.println("Black Wins");
                }
                break;
            }


            String currentPlayer = engine.getTurn() == engine.black ? "Black" : "White";
            System.out.println("It is " + currentPlayer + "'s turn!");



            if (engine.getTurn() == player) {

                System.out.println("Make your move " + currentPlayer + "!\n");

                while (!choiceConfirmed) {
                    System.out.println("Available moves:");
                    for (int i = 0; i < moves.size(); i++) {
                        System.out.println(i + 1 + ": " + moves.get(i));
                    }
                    System.out.println(draw.printBoard());

                    choiceNr = Input.preview(scanner,moves);
                    System.out.println(draw.printPreviewBoard(moves.get(choiceNr)));
                    choiceConfirmed = Input.confirm(scanner,choiceNr);

                    if (choiceConfirmed) {
                        engine.playerMove(moves.get(choiceNr));
                    }

                }

            } else {
                String aiMove = ai.getComputerMove();


                if (!engine.playerMove(aiMove))
                    System.out.println("false");

                System.out.println("Ai moved: " + aiMove);
                System.out.println(draw.printPreviewBoard(aiMove)); // todo discuss this one
            }

            turnsLeft--;
        }
    }

}
