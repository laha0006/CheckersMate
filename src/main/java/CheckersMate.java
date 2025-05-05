import java.util.*;

public class CheckersMate {

    public static void main(String[] args) {
        int[] testBoard1 = new int[] {
                Board.blackKing, Board.blackKing, Board.black, Board.black,
                Board.black, Board.white, 0, 0,
                0, 0, 0, 0,
                0, Board.white, Board.white, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        };
        int[] testBoard2 = new int[] {
                0, 0, 0, 0,
                Board.black, Board.black, Board.black, Board.black,
                0, Board.white, 0, 0,
                0, Board.white, Board.white, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        };
        int[] testBoard3 = new int[] {
                0, 0, 0, 0,
                Board.black, Board.black, Board.black, Board.black,
                0, Board.white, 0, 0,
                0, Board.white, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        };

        int[] board = Board.createStartBoard();
        //board = testBoard2;

        // for (int i = 0; i < board.length; i++) {
        // System.out.println(board[i]);
        // }
        // playerMove("9-14");
        // playerMove("34-14");

        // print board
        System.out.println(Board.moveGuide());

        // System.out.println(legalJumpMove(0,9,
        // testBoard,black,CreateLegalJumpMoveMap()));
        int turnsLeft = 100;
        int turn = Board.black;

        Scanner scanner = new Scanner(System.in);
        String choice;

        while(turnsLeft > 0) {
            System.out.println(Board.printBoard(board));

            String currentPlayer = turn == Board.black ? "Black" : "White";
            System.out.println("It is " + currentPlayer+"'s turn!");

            /*
            List<Integer> movablePieces = Engine.getMovablePieces(board, turn);
            System.out.println("Available pieces:");
            for (int piece : movablePieces) {
                System.out.println(piece);
            }
            */

            List<String> moves = Engine.getMovesForTurn(board, turn);
            System.out.println("Available moves:");

            for (String move : moves) {
                System.out.println(move);
            }

            System.out.println("Make your move "+currentPlayer+"!");

            choice = scanner.nextLine();

            while(!Engine.playerMove(board,turn,choice)){
                System.out.println("Impossible move! Try again!");
                choice = scanner.nextLine();
            }

            turn = turn == Board.black ? Board.white : Board.black;
            turnsLeft--;
        }
        // System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }

}
