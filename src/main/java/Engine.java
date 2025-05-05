import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Engine {

    private static final Map<Integer, Set<Integer>> LEGAL_SIMPLE_MOVE_MAP = MoveMaps.createLegalSimpleMoveMap();
    private static final Map<Integer, Set<Integer>> LEGAL_JUMP_MOVE_MAP = MoveMaps.createLegalJumpMoveMap();
    private static final Map<Integer, Integer> JUMP_OVER_INDEX_MAP = MoveMaps.createJumpOverIndexMap();

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

        // System.out.println("FROM: " + from);
        // System.out.println("TO: " + to);
    }

    public static boolean isLegalSimpleMove(int from, int to, int[] board, int turn) {
        Set<Integer> moves = LEGAL_SIMPLE_MOVE_MAP.get(from);
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
        if (!isEmpty(boardToPosition) || isEmpty(boardFromPosition)) {
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

    public static boolean isLegalJumpMove(int from, int to, int[] board, int turn) {
        //
        // System.out.println("from:" + from + " to:" + to);
        int sum = from + to;
        int diff = from - to;
        int jumpOverIndex = JUMP_OVER_INDEX_MAP.get(sum);
        int fromPiece = board[from];
        int jumpOverPiece = board[jumpOverIndex];

        if (!isEmpty(board[to])) {
            return false;
        }

        // true
        if (turn == Board.black) {
            return isWhite(jumpOverPiece) && isBlack(fromPiece) && (diff < 0 || isBlackKing(fromPiece));
        }
        if (turn == Board.white) {
            return isBlack(jumpOverPiece) && isWhite(fromPiece) && (diff > 0 || isWhiteKing(fromPiece));
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

    public static boolean turnAndPieceMatches(int turn, int piece) {
        return (turn == Board.black && isBlack(piece)) || (turn == Board.white && isWhite(piece));
    }

    public static List<String> getMovesForTurn(int[] board, int turn) {
        List<String> moveStrings = getJumpMovesForTurn(board, turn);

        if (moveStrings.isEmpty()) {
            moveStrings = getSimpleMovesForTurn(board, turn);
        }
        return moveStrings;
    }

    public static List<String> getJumpMovesForTurn(int[] board, int turn) {
        List<String> jumpMoves = new ArrayList<>();
        for (int i = 0; i < Board.boardSize; i++) {
            jumpMoves.addAll(addJumpMoveSequenceStrings(i, board, turn, Integer.toString(i), new HashSet<String>(),
                    new ArrayList<String>()));
        }
        return jumpMoves;
    }

    public static List<String> getSimpleMovesForTurn(int[] board, int turn) {
        List<String> simpleMoveStrings = new ArrayList<>();
        for (int position = 0; position < board.length; position++) {
            Set<Integer> moves = LEGAL_SIMPLE_MOVE_MAP.get(position);
            for (int move : moves) {
                if (isLegalSimpleMove(position, move, board, turn)) {
                    String moveString = (Integer.toString(position) + "-" + Integer.toString(move));
                    simpleMoveStrings.add(moveString);
                }
            }
        }
        return simpleMoveStrings;
    }

    public static List<String> addJumpMoveSequenceStrings(int position, int[] board, int turn, String jumpSequence,
            Set<String> visisted, List<String> jumpSequences) {
        Set<Integer> moves = LEGAL_JUMP_MOVE_MAP.get(position);
        boolean jumped = false;
        for (int move : moves) {
            String moveString = Integer.toString(position) + Integer.toString(move);
            String moveStringTwo = Integer.toString(move) + Integer.toString(position);
            if (isLegalJumpMove(position, move, board, turn) && !visisted.contains(moveString)) {
                visisted.add(moveString); // TODO: consider if using jumpOverIndex makes sense here.
                visisted.add(moveStringTwo);
                // System.out.println("moveString:" + moveString);
                // System.out.println("moveStringTwo:" + moveStringTwo);
                // System.out.println();
                String oldSequence = jumpSequence;
                jumpSequence += "x" + move;
                jumped = true;
                board[move] = board[position];
                int old = board[position];
                board[position] = Board.empty;
                addJumpMoveSequenceStrings(move, board, turn, jumpSequence, visisted, jumpSequences);
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

    public static List<Integer> getMovablePieces(int[] board, int turn) {
        List<Integer> movablePieces = new ArrayList<>();
        Set<Integer> moves;

        int pieceType;
        for (int pieceIndex = 0; pieceIndex < board.length; pieceIndex++) {
            pieceType = board[pieceIndex];
            if (!turnAndPieceMatches(turn, pieceType)) {
                continue;
            }
            
            moves = LEGAL_JUMP_MOVE_MAP.get(pieceIndex);

            for (int jumpMove : moves) {
                if (isLegalJumpMove(pieceIndex, jumpMove, board, turn)) {
                    movablePieces.add(pieceIndex);
                    continue;
                }
            }
        }
        if (movablePieces.isEmpty()) {
            for (int pieceIndex = 0; pieceIndex < board.length; pieceIndex++) {
                pieceType = board[pieceIndex];
                if (!turnAndPieceMatches(turn, pieceType)) {
                    continue;
                }

                moves = LEGAL_SIMPLE_MOVE_MAP.get(pieceIndex);

                for (int simpleMove : moves) {
                    if (isLegalSimpleMove(pieceIndex, simpleMove, board, turn)) {
                        movablePieces.add(pieceIndex);
                        break;
                    }
                }
            }
        }
        return movablePieces;
    }

    // public static List<String> getAvailableMovesForPosition(int position, int[]
    // board, int turn) {
    // List<String> availableMoves = new ArrayList<>();

    // availableMoves.addAll(addJumpSequenceStrings(position, board, turn,
    // CreateLegalJumpMoveMap(), Integer.toString(position), new HashSet<String>(),
    // new ArrayList<String>()));
    // if (availableMoves.isEmpty()) {
    // availableMoves.addAll(getSimpleMoveStrings())
    // }
    // return
    // }

    // private static List<String> getJumpSequences(int position, int[] board, int
    // turn) {
    // Map<Integer, Set<Integer>> jumpMoveSet = CreateLegalJumpMoveMap();
    // Set<Integer> jumpMoves = jumpMoveSet.get(position);
    //
    // for (Integer jumpDestination : jumpMoves) {
    // String move;
    // if (legalJumpMove(position, jumpDestination, board, turn, jumpMoveSet)) {
    // move = position + "x" + jumpDestination;
    // }
    // }
    // // 1. look at all possible jump moves from position
    // // 2. if we find a legal move, append to move, and go to 1
    // // 3. if no legal moves are found, add move to list, and go to 1
    // }

    // private static List<String> getMovesForPosition(int position, int turn, int[]
    // board) {
    // Map<Integer, Set<Integer>> simpleMoveSet = CreateLegalSimpleMoveMap();
    // Map<Integer, Set<Integer>> jumpMoveSet = CreateLegalJumpMoveMap();
    //
    // Set<Integer> simpleMoves = simpleMoveSet.get(position);
    // Set<Integer> jumpMoves = jumpMoveSet.get(position);
    //
    // List<String> moves = new ArrayList<>();
    //
    // int currentPiece = board[position];
    //
    // // check set of moves in simpleMoveSet
    // moves.addAll(simpleMoves
    // .stream()
    // .map((Integer destination) -> {
    // if (isLegalMove(position, destination, board, turn, simpleMoveSet)) {
    // return position + "-" + destination;
    // }
    // return "";
    // })
    // .filter((String move) -> !move.isEmpty())
    // .toList());
    // // check set of moves in jumpMoveSet
    //
    // // check if they are legal moves
    // // if they are legal push to moves
    // return null;
    // }

}
