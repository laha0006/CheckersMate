import java.util.*;

public class CheckersMate {


    public static void main(String[] args) {
        int[] board = Board.createStartBoard();
//        for (int i = 0; i < board.length; i++) {
//            System.out.println(board[i]);
//        }
//        playerMove("9-14");
//        playerMove("34-14");
        
        int[] testBoard = new int[] {Board.blackKing,Board.blackKing,Board.black,Board.black,Board.black,
                Board.white,0,0,0,0,0,0,0,Board.white,Board.white,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        //print board
        System.out.println(Board.moveGuide());
        System.out.println(Board.printBoard(testBoard));

        //        System.out.println(legalJumpMove(0,9, testBoard,black,CreateLegalJumpMoveMap()));
        List<String> jumpMoves = Engine.getMovesForTurn(Board.black, testBoard );
        for (String move : jumpMoves) {
            System.out.println(jumpMoves.indexOf(move) + " " +  move);
        }
//        System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }



    

}
