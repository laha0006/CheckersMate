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

        // int[] board = Board.createStartBoard();
        int[] board = testBoard2;
        // for (int i = 0; i < board.length; i++) {
        // System.out.println(board[i]);
        // }
        // playerMove("9-14");
        // playerMove("34-14");

        // print board
        System.out.println(Board.moveGuide());
        System.out.println(Board.printBoard(board));

        // System.out.println(legalJumpMove(0,9,
        // testBoard,black,CreateLegalJumpMoveMap()));

        List<Integer> movablePieces = Engine.getMovablePieces(board, Board.black);
        for (int piece : movablePieces) {
            System.out.println(piece);
        }

        List<String> moves = Engine.getMovesForTurn(board, Board.black);
        for (String move : moves) {
            System.out.println(move);
        }

        // System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }

}
