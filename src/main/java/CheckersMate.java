import java.util.*;

public class CheckersMate {


    public static void main(String[] args) {
        int[] board = Board.createStartBoard();
//        for (int i = 0; i < board.length; i++) {
//            System.out.println(board[i]);
//        }
//        playerMove("9-14");
//        playerMove("34-14");
        Map<Integer,Set<Integer>> legalJumpMoves = CreateLegalJumpMoveMap();
        Map<Integer,Set<Integer>> legalSimpleMoves = CreateLegalSimpleMoveMap();
        int[] testBoard = new int[] {Board.blackKing,Board.blackKing,Board.black,Board.black,Board.black,
                Board.white,0,0,0,0,0,0,0,Board.white,Board.white,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        //print board
        System.out.println(Board.moveGuide());
        System.out.println(Board.printBoard(board));

        //        System.out.println(legalJumpMove(0,9, testBoard,black,CreateLegalJumpMoveMap()));
        List<String> jumpMoves = getMovesForTurn(Board.black, testBoard );
        for (String move : jumpMoves) {
            System.out.println(move);
        }
//        System.out.println(legalJumpMove(0, 31, testBoard, black, legalJumpMoves));
    }



    public static void playerMove(String move) {
        String[] parsedString = move.split("-");
        int from = Integer.parseInt(parsedString[0]);
        int to = Integer.parseInt(parsedString[1]);
        if (from > Board.boardSize || from < 0 || to < 0 || to > Board.boardSize) {
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

    public static Map<Integer,Set<Integer>> CreateLegalSimpleMoveMap() {
       Map<Integer,Set<Integer>> legalMoveMap = new HashMap<>();
       legalMoveMap.put(0, new HashSet<>(List.of(4,5)));
       legalMoveMap.put(1, new HashSet<>(List.of(5,6)));
       legalMoveMap.put(2, new HashSet<>(List.of(6,7)));
       legalMoveMap.put(3, new HashSet<>(List.of(7)));
       legalMoveMap.put(4, new HashSet<>(List.of(0,8)));
       legalMoveMap.put(5, new HashSet<>(List.of(0,1, 8,9)));
       legalMoveMap.put(6, new HashSet<>(List.of(1,2, 9,10)));
       legalMoveMap.put(7, new HashSet<>(List.of(2,3, 10,11)));
       legalMoveMap.put(8, new HashSet<>(List.of(4,5, 12,13)));
       legalMoveMap.put(9, new HashSet<>(List.of(5,6, 13,14)));
       legalMoveMap.put(10, new HashSet<>(List.of(6,7, 14,15)));
       legalMoveMap.put(11, new HashSet<>(List.of(7,15)));
       legalMoveMap.put(12, new HashSet<>(List.of(8,16)));
       legalMoveMap.put(13, new HashSet<>(List.of(8,9,16,17)));
       legalMoveMap.put(14, new HashSet<>(List.of(9,10,17,18)));
       legalMoveMap.put(15, new HashSet<>(List.of(10,11,18,19)));
       legalMoveMap.put(16, new HashSet<>(List.of(12,13,20,21)));
       legalMoveMap.put(17, new HashSet<>(List.of(13,14,21,22)));
       legalMoveMap.put(18, new HashSet<>(List.of(14,15,22,23)));
       legalMoveMap.put(19, new HashSet<>(List.of(15,23)));
       legalMoveMap.put(20, new HashSet<>(List.of(16,24)));
       legalMoveMap.put(21, new HashSet<>(List.of(16,17,24,25)));
       legalMoveMap.put(22, new HashSet<>(List.of(17,18,25,26)));
       legalMoveMap.put(23, new HashSet<>(List.of(18,19,26,27)));
       legalMoveMap.put(24, new HashSet<>(List.of(20,21,28,29)));
       legalMoveMap.put(25, new HashSet<>(List.of(21,22,29,30)));
       legalMoveMap.put(26, new HashSet<>(List.of(22,23,30,31)));
       legalMoveMap.put(27, new HashSet<>(List.of(23,31)));
       legalMoveMap.put(28, new HashSet<>(List.of(24)));
       legalMoveMap.put(29, new HashSet<>(List.of(24,25)));
       legalMoveMap.put(30, new HashSet<>(List.of(25,26)));
       legalMoveMap.put(31, new HashSet<>(List.of(26,27)));


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
// TODO: FIX values
    public static Map<Integer, Integer> createJumpOverIndexMap() {
        Map<Integer, Integer> jumpOverIndexMap = new HashMap<>();
        jumpOverIndexMap.put(9, 5);
        jumpOverIndexMap.put(11, 6);
        jumpOverIndexMap.put(13, 7);

        jumpOverIndexMap.put(17, 8);
        jumpOverIndexMap.put(19, 9);
        jumpOverIndexMap.put(21, 10);

        jumpOverIndexMap.put(25, 13);
        jumpOverIndexMap.put(27, 14);
        jumpOverIndexMap.put(29, 15);

        jumpOverIndexMap.put(33, 16);
        jumpOverIndexMap.put(35, 17);
        jumpOverIndexMap.put(37, 18);

        jumpOverIndexMap.put(41, 21);
        jumpOverIndexMap.put(43, 22);
        jumpOverIndexMap.put(45, 23);

        jumpOverIndexMap.put(49, 24);
        jumpOverIndexMap.put(51, 25);
        jumpOverIndexMap.put(53, 26);
        return jumpOverIndexMap;
    }

    public static boolean isLegalMove(int from, int to, int[] board, int turn, Map<Integer,Set<Integer>> legalMoveMap) {
        Set<Integer> moves = legalMoveMap.get(from);
        int boardFromPosition = board[from];
        int boardToPosition = board[to];

        int difference = to - from;

        if (turn == Board.black && difference < 0 && !isKing(boardFromPosition)) {
            return false;
        }

        if (turn == Board.white && difference > 0 && !isKing(boardFromPosition)) {
            return false;
        }

        if (!moves.contains(to)) {
            return false;
        }
        if ( !isEmpty(boardToPosition) || isEmpty(boardFromPosition) ) {
            return false;
        }
        if (turn == Board.black && (boardFromPosition == Board.white || isWhiteKing(boardFromPosition))) {
            return false;
        }
        if (turn == Board.white && (boardFromPosition == Board.black || isBlackKing(boardFromPosition))) {
            return false;
        }

        return true;
    }

    public static boolean legalSimpleMove(int from, int to, int[] board ,int turn, Map<Integer, Set<Integer>> legalMoveMap) {
        return isLegalMove(from, to, board, turn, legalMoveMap);
    }

    public static boolean legalJumpMove(int from, int to, int[] board, int turn, Map<Integer,Set<Integer>> legalMoveMap) {
//        if(!isLegalMove(from, to, board, turn, legalMoveMap)) {
//            return false;
//        }
//        System.out.println("from:" + from + " to:" + to);
        Map<Integer, Integer> jumpOverIndexes = createJumpOverIndexMap();
        int sum = from + to;
        int diff = from - to;
        int jumpOverIndex = jumpOverIndexes.get(sum);
        int fromPiece = board[from];
        int jumpOverPiece = board[jumpOverIndex];

        if ( !isEmpty(board[to]) ) {
            return false;
        }


        // true
        if (turn == Board.black) {
            return isWhite(jumpOverPiece) && isBlack(fromPiece) && ( diff < 0 || isBlackKing(fromPiece));
        }
        if (turn == Board.white) {
            return isBlack(jumpOverPiece) && isWhite(fromPiece) && ( diff > 0 || isWhiteKing(fromPiece));
        }
        return true;
    }

    public static boolean isBlack(int piece) {
        return piece == Board.black || isBlackKing(piece);
    }
    public static boolean isWhite(int piece) {
        return piece == Board.white || isWhiteKing(piece);
    }

    public static boolean isBlackKing(int piece) {
        return piece == Board.blackKing;
    }
    
    public static boolean isWhiteKing(int piece) {
        return piece == Board.whiteKing;
    }
    
    public static boolean isKing(int piece) {
        return isBlackKing(piece) || isWhiteKing(piece);
    }

    public static boolean isEmpty(int tile) {
        return tile == Board.empty;
    }
    
    public static List<String> getMovesForTurn(int turn, int[] board) {
        List<String> jumpMoves = new ArrayList<>();
        List<String> simpleMoves = new ArrayList<>();
        for (int i = 0; i < Board.boardSize; i++) {
//          addSimpleMoves()
            jumpMoves.addAll(addJumpSequenceStrings(i, board, turn, CreateLegalJumpMoveMap(),Integer.toString(i), new HashSet<String>(), new ArrayList<String>()));
        }


        return jumpMoves;
    }

    public static List<String> addJumpSequenceStrings(int position, int[] board, int turn, Map<Integer, Set<Integer>> jumpMoveSet, String jumpSequence, Set<String> visisted, List<String> jumpSequences) {
        Set<Integer> moves = jumpMoveSet.get(position);
        boolean jumped = false;
        for(int move : moves) {
            String moveString = Integer.toString(position) + Integer.toString(move);
            String moveStringTwo = Integer.toString(move) + Integer.toString(position);
            if(legalJumpMove(position, move, board, turn, jumpMoveSet) && !visisted.contains(moveString)) {
                visisted.add(moveString);    //TODO: consider if using jumpOverIndex makes sense here.
                visisted.add(moveStringTwo);
                System.out.println("moveString:" + moveString);
                System.out.println("moveStringTwo:" + moveStringTwo);
                System.out.println();
                String oldSequence = jumpSequence;
                jumpSequence += "x" + move;
                jumped = true;
                board[move] = board[position];
                int old = board[position];
                board[position] = Board.empty;
                addJumpSequenceStrings(move, board, turn, jumpMoveSet, jumpSequence, visisted, jumpSequences );
                board[move] = Board.empty;
                board[position] = old;
                jumpSequence = oldSequence;
            }


        }

        if (!jumped && jumpSequence.contains("x")) {
            jumpSequences.add(jumpSequence);
        }

        return jumpSequences;
    }

//    private static List<String> getJumpSequences(int position, int[] board, int turn) {
//        Map<Integer, Set<Integer>> jumpMoveSet = CreateLegalJumpMoveMap();
//        Set<Integer> jumpMoves = jumpMoveSet.get(position);
//
//        for (Integer jumpDestination : jumpMoves) {
//            String move;
//            if (legalJumpMove(position, jumpDestination, board, turn, jumpMoveSet)) {
//                move = position + "x" + jumpDestination;
//            }
//        }
//        // 1. look at all possible jump moves from position
//        // 2. if we find a legal move, append to move, and go to 1
//        // 3. if no legal moves are found, add move to list, and go to 1
//    }

//    private static List<String> getMovesForPosition(int position, int turn, int[] board) {
//        Map<Integer, Set<Integer>> simpleMoveSet = CreateLegalSimpleMoveMap();
//        Map<Integer, Set<Integer>> jumpMoveSet = CreateLegalJumpMoveMap();
//
//        Set<Integer> simpleMoves = simpleMoveSet.get(position);
//        Set<Integer> jumpMoves = jumpMoveSet.get(position);
//
//        List<String> moves = new ArrayList<>();
//
//        int currentPiece = board[position];
//
//        // check set of moves in simpleMoveSet
//        moves.addAll(simpleMoves
//                .stream()
//                .map((Integer destination) -> {
//                    if (isLegalMove(position, destination, board, turn, simpleMoveSet)) {
//                        return position + "-" + destination;
//                    }
//                    return "";
//                })
//                .filter((String move) -> !move.isEmpty())
//                .toList());
//        // check set of moves in jumpMoveSet
//
//        // check if they are legal moves
//        // if they are legal push to moves
//        return null;
//    }

}
