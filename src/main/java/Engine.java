import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Engine {
    public final int black = 1;
    public final int blackPawn = 1;
    public final int blackKing = 11;

    public final int white = 2;
    public final int whitePawn = 2;
    public final int whiteKing = 22;

    public final int empty = 0;

    private final Map<Integer, Set<Integer>> LEGAL_SIMPLE_MOVE_MAP = MoveMaps.createLegalSimpleMoveMap();
    private final Map<Integer, Set<Integer>> LEGAL_JUMP_MOVE_MAP = MoveMaps.createLegalJumpMoveMap();
    private final Map<Integer, Integer> JUMP_OVER_INDEX_MAP = MoveMaps.createJumpOverIndexMap();

    public Board board;
    int turn;

    public Engine(Board board, int startTurn) {
        this.board = board;
        turn = startTurn;
    }

    public void flipTurn() {
        turn = turn == black ? white : black;
    }

    public boolean playerMove(String move) {
        System.out.println(getMovesForTurn());
        if(!getMovesForTurn().contains(move)) {
            System.out.println("Not in move list!");
            return false;
        }

        String[] parsedString;
        int moveType;
        if(move.contains("-")){
            parsedString = move.split("-");
            moveType = 1;
        }
        else if(move.contains("x")){
            parsedString = move.split("x");
            moveType = 2;
        }else{
            return false;
        }

        int length = parsedString.length;

        if(moveType == 1){
            int from = Integer.parseInt(parsedString[0]);
            int to = Integer.parseInt(parsedString[1]);
            board.move(from, to);
            flipTurn();
            return true;
        }
        else {
            int jumps = length - 1;
            int from;
            int to;
            int[] eliminations = new int[jumps];

            for(int i = 0; i < jumps; i++) {
                from = Integer.parseInt(parsedString[i]);
                to = Integer.parseInt(parsedString[i+1]);
                eliminations[i] = JUMP_OVER_INDEX_MAP.get(from+to);
            }
            int firstFrom = Integer.parseInt(parsedString[0]);
            int finalTo = Integer.parseInt(parsedString[length - 1]);
            board.jump(firstFrom,finalTo, eliminations);
            flipTurn();
            return true;
        }
    }

    public boolean isLegalSimpleMove(int from, int to) {
        Set<Integer> moves = LEGAL_SIMPLE_MOVE_MAP.get(from);
        int boardFromPosition = board.getBoard()[from];
        int boardToPosition = board.getBoard()[to];

        int difference = to - from;

        if (!turnAndPieceMatches(boardFromPosition)) {
            return false;
        }
        
        if (isBlackPawn(boardFromPosition) && difference < 0) {
            return false;
        }

        if (isWhitePawn(boardFromPosition) && difference > 0) {
            return false;
        }

        if (!moves.contains(to)) {
            return false;
        }
        if (!isEmpty(boardToPosition) || isEmpty(boardFromPosition)) {
            return false;
        }
        
        
        return true;
    }

    public boolean isLegalJumpMove(int from, int to) {
        //
        // System.out.println("from:" + from + " to:" + to);
        int sum = from + to;
        int diff = from - to;
        int jumpOverIndex = JUMP_OVER_INDEX_MAP.get(sum);
        int fromPiece = board.getBoard()[from];
        int jumpOverPiece = board.getBoard()[jumpOverIndex];

        if (!isEmpty(board.getBoard()[to])) {
            return false;
        }

        // true
        if (turn == black) {
            return isWhite(jumpOverPiece) && isBlack(fromPiece) && (diff < 0 || isBlackKing(fromPiece));
        }
        if (turn == white) {
            return isBlack(jumpOverPiece) && isWhite(fromPiece) && (diff > 0 || isWhiteKing(fromPiece));
        }
        return true;
    }

    public boolean isBlack(int piece) {
        return piece == blackPawn || isBlackKing(piece);
    }

    public boolean isWhite(int piece) {
        return piece == whitePawn || isWhiteKing(piece);
    }

    public boolean isBlackKing(int piece) {
        return piece == blackKing;
    }

    public boolean isWhiteKing(int piece) {
        return piece == whiteKing;
    }

    public boolean isBlackPawn(int piece) {
        return piece == blackPawn;
    }

    public boolean isWhitePawn(int piece) {
        return piece == whitePawn;
    }

    public boolean isKing(int piece) {
        return isBlackKing(piece) || isWhiteKing(piece);
    }

    public boolean isEmpty(int tile) {
        return tile == empty;
    }

    public boolean turnAndPieceMatches(int piece) {
        return (turn == black && isBlack(piece)) || (turn == white && isWhite(piece));
    }

    public List<String> getMovesForTurn() {
        List<String> moveStrings = getJumpMovesForTurn();

        if (moveStrings.isEmpty()) {
            moveStrings = getSimpleMovesForTurn();
        }
        return moveStrings;
    }

    public List<String> getJumpMovesForTurn() {
        List<String> jumpMoves = new ArrayList<>();

        for (int i = 0; i < board.getBoard().length; i++) {

            jumpMoves.addAll(addJumpMoveSequenceStrings(i,Integer.toString(i), new HashSet<String>(),
                    new ArrayList<String>()));
        }
        return jumpMoves;
    }

    public List<String> getSimpleMovesForTurn() {
        List<String> simpleMoveStrings = new ArrayList<>();
        for (int position = 0; position < board.getBoard().length; position++) {
            Set<Integer> moves = LEGAL_SIMPLE_MOVE_MAP.get(position);
            for (int move : moves) {
                if (isLegalSimpleMove(position, move)) {
                    String moveString = (position + "-" + move);
                    simpleMoveStrings.add(moveString);
                }
            }
        }
        return simpleMoveStrings;
    }

    public List<String> addJumpMoveSequenceStrings(int position, String jumpSequence, Set<String> visisted, List<String> jumpSequences) {
        Set<Integer> moves = LEGAL_JUMP_MOVE_MAP.get(position);
        boolean jumped = false;
        for (int move : moves) {
            if (isLegalJumpMove(position, move)) {
                String oldSequence = jumpSequence;
                jumpSequence += "x" + move;
                jumped = true;
                int sum = position + move;
                board.singleJump(position, move, JUMP_OVER_INDEX_MAP.get(sum));
                addJumpMoveSequenceStrings(move, jumpSequence, visisted, jumpSequences);
                board.reset();
                jumpSequence = oldSequence;
            }

        }

        if (!jumped && jumpSequence.contains("x")) {
            jumpSequences.add(jumpSequence);
        }
        return jumpSequences;
    }

    public List<Integer> getMovablePieces() {
        List<Integer> movablePieces = new ArrayList<>();
        Set<Integer> moves;

        int pieceType;
        for (int pieceIndex = 0; pieceIndex < board.getBoard().length; pieceIndex++) {
            pieceType = board.getBoard()[pieceIndex];
            if (!turnAndPieceMatches(pieceType)) {
                continue;
            }
            
            moves = LEGAL_JUMP_MOVE_MAP.get(pieceIndex);

            for (int jumpMove : moves) {
                if (isLegalJumpMove(pieceIndex, jumpMove)) {
                    movablePieces.add(pieceIndex);
                    break;
                }
            }
        }
        if (movablePieces.isEmpty()) {
            for (int pieceIndex = 0; pieceIndex < board.getBoard().length; pieceIndex++) {
                pieceType = board.getBoard()[pieceIndex];
                if (!turnAndPieceMatches(pieceType)) {
                    continue;
                }

                moves = LEGAL_SIMPLE_MOVE_MAP.get(pieceIndex);

                for (int simpleMove : moves) {
                    if (isLegalSimpleMove(pieceIndex, simpleMove)) {
                        movablePieces.add(pieceIndex);
                        break;
                    }
                }
            }
        }
        return movablePieces;
    }

    public int getTurn() {
        return turn;
    }

    public Board getState() {
        return board;
    }

    // public List<String> getAvailableMovesForPosition(int position, int[]
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

    // private List<String> getJumpSequences(int position, int[] board, int
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

    // private List<String> getMovesForPosition(int position, int turn, int[]
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
