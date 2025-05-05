public class Board {
    public static final int black = 1;
    public static final int blackKing = 11;
    public static final int white = 2;
    public static final int whiteKing = 22;
    public static final int empty = 0;

    public static final int boardSize = 32;

    public static int[] createStartBoard() {
        int[] boardState = new int[boardSize];
        for (int i = 0; i < 12; i++) {
            boardState[i] = black;
        }
        for (int i = 20; i < 32;  i++  ) {
            boardState[i] = white;
        }
        return boardState;
    }
}
