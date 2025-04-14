import java.util.*;

public class CheckersMate {
    public static final int black = 1;
    public static final int blackKing = 11;
    public static final int white = 2;
    public static final int whiteKing = 22;
    public static final int empty = 0;
    public static void main(String[] args) {
        int[] board = createStartBoard();
//        for (int i = 0; i < board.length; i++) {
//            System.out.println(board[i]);
//        }
//        playerMove("9-14");
//        playerMove("34-14");
        Map<Integer,Set<Integer>> legalJumpMoves = CreateLegalJumpMoveMap();
        int[] testBoard = new int[] {black,black,black,black,black,white,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }

    public static int[] createStartBoard() {
        int[] boardState = new int[32];
        for (int i = 0; i < 12; i++) {
            boardState[i] = black;
        }
        for (int i = 20; i < 32;  i++  ) {
            boardState[i] = white;
        }
        return boardState;
    }

    public static void playerMove(String move) {
        String[] parsedString = move.split("-");
        int from = Integer.parseInt(parsedString[0]);
        int to = Integer.parseInt(parsedString[1]);
        if (from > 32 || from < 0 || to < 0 || to > 32) {
            System.out.println("out of bounds move: " + move);
            return;
        }
        if (from == to) {
            System.out.println("Not a valid move: " + move);
            return;
        }

//        System.out.println("FROM: " + from);
//        System.out.println("TO: " + to);
    }

    public static Map<Integer,int[]> CreateLegalSimpleMoveMap() {
       Map<Integer,int[]> legalMoveMap = new HashMap<Integer,int[]>();
       legalMoveMap.put(0, new int[] {4,5});
       legalMoveMap.put(1, new int[] {5,6});
       legalMoveMap.put(2, new int[] {6,7});
       legalMoveMap.put(3, new int[] {7});
       legalMoveMap.put(4, new int[] {0,8});
       legalMoveMap.put(5, new int[] {0,1, 8,9});
       legalMoveMap.put(6, new int[] {1,2, 9,10});
       legalMoveMap.put(7, new int[] {2,3, 10,11});
       legalMoveMap.put(8, new int[] {4,5, 12,13});
       legalMoveMap.put(9, new int[] {5,6, 13,14});
       legalMoveMap.put(10, new int[] {6,7, 14,15});
       legalMoveMap.put(11, new int[] {7,15});
       legalMoveMap.put(12, new int[] {8,16});
       legalMoveMap.put(13, new int[] {8,9,16,17});
       legalMoveMap.put(14, new int[] {9,10,17,18});
       legalMoveMap.put(15, new int[] {10,11,18,19});
       legalMoveMap.put(16, new int[] {12,13,20,21});
       legalMoveMap.put(17, new int[] {13,14,21,22});
       legalMoveMap.put(18, new int[] {14,15,22,23});
       legalMoveMap.put(19, new int[] {15,23});
       legalMoveMap.put(20, new int[] {16,24});
       legalMoveMap.put(21, new int[] {16,17,24,25});
       legalMoveMap.put(22, new int[] {17,18,25,26});
       legalMoveMap.put(23, new int[] {18,19,26,27});
       legalMoveMap.put(24, new int[] {20,21,28,29});
       legalMoveMap.put(25, new int[] {21,22,29,30});
       legalMoveMap.put(26, new int[] {22,23,30,31});
       legalMoveMap.put(27, new int[] {23,31});
       legalMoveMap.put(28, new int[] {24});
       legalMoveMap.put(29, new int[] {24,25});
       legalMoveMap.put(30, new int[] {25,26});
       legalMoveMap.put(31, new int[] {26,27});


       return legalMoveMap;
    }

    public static Map<Integer,Set<Integer>> CreateLegalJumpMoveMap() {
       Map<Integer,Set<Integer>> legalMoveMap = new HashMap<>();
       legalMoveMap.put(0, new HashSet<>(List.of(9)));
       legalMoveMap.put(1, new HashSet<>(List.of(8,10)));
       legalMoveMap.put(2, new HashSet<>(List.of(9,11)));
       legalMoveMap.put(3, new HashSet<>(List.of(10)));
       legalMoveMap.put(4, new HashSet<>(List.of(13)));
       legalMoveMap.put(5, new HashSet<>(List.of(12,14)));
       legalMoveMap.put(6, new HashSet<>(List.of(13,15)));
       legalMoveMap.put(7, new HashSet<>(List.of(14)));
       legalMoveMap.put(8, new HashSet<>(List.of(1,17)));
       legalMoveMap.put(9, new HashSet<>(List.of(0,2,16,18)));
       legalMoveMap.put(10, new HashSet<>(List.of(1,3,17,19)));
       legalMoveMap.put(11, new HashSet<>(List.of(2,18)));
       legalMoveMap.put(12, new HashSet<>(List.of(5,21)));
       legalMoveMap.put(13, new HashSet<>(List.of(4,6,20,22)));
       legalMoveMap.put(14, new HashSet<>(List.of(5,7,21,23)));
       legalMoveMap.put(15, new HashSet<>(List.of(6,22)));
       legalMoveMap.put(16, new HashSet<>(List.of(9,25)));
       legalMoveMap.put(17, new HashSet<>(List.of(8,10,24,26)));
       legalMoveMap.put(18, new HashSet<>(List.of(9,11,25,27)));
       legalMoveMap.put(19, new HashSet<>(List.of(10,26)));
       legalMoveMap.put(20, new HashSet<>(List.of(13,29)));
       legalMoveMap.put(21, new HashSet<>(List.of(12,14,28,30)));
       legalMoveMap.put(22, new HashSet<>(List.of(13,15,29,31)));
       legalMoveMap.put(23, new HashSet<>(List.of(14,30)));
       legalMoveMap.put(24, new HashSet<>(List.of(17)));
       legalMoveMap.put(25, new HashSet<>(List.of(16,18)));
       legalMoveMap.put(26, new HashSet<>(List.of(17,19)));
       legalMoveMap.put(27, new HashSet<>(List.of(18)));
       legalMoveMap.put(28, new HashSet<>(List.of(21)));
       legalMoveMap.put(29, new HashSet<>(List.of(20,22)));
       legalMoveMap.put(30, new HashSet<>(List.of(21,23)));
       legalMoveMap.put(31, new HashSet<>(List.of(22)));


       return legalMoveMap;
    }

    public static boolean legalJumpMove(int from, int to, int[] board, int turn, Map<Integer,Set<Integer>> legalMoveMap) {
        //0-31

        Set<Integer> moves = legalMoveMap.get(from);
        if (!moves.contains(to)) {
            System.out.println("ILLEGAL JUMP");
            return false;
        }

        int row = from / 4;
        int jumpOverIndex;
        if (row % 2 == 0) {
            jumpOverIndex = (from+1 + to) / 2;
        } else {
            jumpOverIndex = (from-1 + to) / 2;
        }
        System.out.println("row: " + row);
        System.out.println("jumpOverIndex: " + jumpOverIndex);
        int piece = board[jumpOverIndex];
        if (turn == black) {
            return piece == white || piece == whiteKing;
        }
        if (turn == white) {
            return piece == black || piece == blackKing;
        }
        return false;
    }

    //

}
