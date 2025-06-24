import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int black = 1;
    private final int blackPawn = 1;
    private final int blackKing = 11;

    private final int white = 2;
    private final int whitePawn = 2;
    private final int whiteKing = 22;

    private final int empty = 0;
    private final int boardSize = 32;


    private int[] board;
    private int[] old;
    private int[] saved;
    private List<int[]> oldBoards = new ArrayList<>();
    private List<int[]> savedBoards = new ArrayList<>();

    public Board() {
        board = createStartBoard();
    }

    private int[] createStartBoard() {
        int[] boardState = new int[boardSize];
        for (int i = 0; i < 12; i++) {
            boardState[i] = blackPawn;
        }
        for (int i = 20; i < 32;  i++  ) {
            boardState[i] = whitePawn;
        }
        return boardState;
    }

    public void useTestBoard() {
        board = new int[]
                { blackPawn,empty,whiteKing,blackPawn,
                blackPawn,whitePawn,whitePawn,blackPawn,
                  blackKing,blackPawn,empty,blackPawn,
                empty,whitePawn,whitePawn,empty,
                  empty,empty,empty,empty,
                whitePawn,whitePawn,whitePawn,whitePawn,
                  blackPawn,empty,empty,empty,
                empty,whitePawn,whitePawn,whitePawn};
    }

    //causes null pointer
    public void useTestBoard2() {
        board = new int[]
                { empty,empty,blackPawn, empty,
                        empty, empty, empty, empty,
                        empty,empty,whitePawn,empty,
                        blackPawn,empty,empty,empty,
                        empty,empty,blackPawn,empty,
                        whitePawn,empty,empty,empty,
                        empty,empty,empty,whitePawn,
                        empty,blackKing,blackKing,empty};
    }

    public void useTestBoard3() {
        board = new int[]
                {   empty,empty,empty, empty,
                    empty, blackPawn, blackPawn, empty,
                    whitePawn,empty,empty,empty,
                    empty,empty,empty,empty,
                    empty,empty,empty,empty,
                    empty,empty,blackPawn,empty,
                    whitePawn,whitePawn,empty,empty,
                    empty,empty,empty,empty};
    }


    public void move(int from, int to) {
        oldBoards.add(board.clone());
        int piece = board[from];
        board[from] = empty;
        board[to] = piece;
    }


    public void reset() {
        board = oldBoards.removeLast();
    }

    public void singleJump(int from, int to, int jumpOverIndex) {
        oldBoards.add(board.clone());
        int piece = board[from];
        board[from] = empty;
        board[to] = piece;
        board[jumpOverIndex] = empty;
    }

    public void jump(int from, int to, int[] eliminations) {
        oldBoards.add(board.clone());
        int piece = board[from];
        board[from] = empty;
        board[to] = piece;

        for (int boardPos : eliminations) {
            board[boardPos] = empty;
        }
    }

    public int[] getBoard() {
        return board;
    }

    public void save() {
        savedBoards.add(board.clone());
    }

    public void load() {
        board = savedBoards.removeLast();
    }
}
