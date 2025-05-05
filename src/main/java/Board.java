public class Board {
    //white pieces
    public static final int black = 1;
    public static final int blackKing = 11;
    //black pieces
    public static final int white = 2;
    public static final int whiteKing = 22;
    //empty spots on the board
    public static final int empty = 0;
    //board size (this should not change)
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

    public int getBlack() {
        return black;
    }

    public int getBlackKing() {
        return blackKing;
    }

    public int getWhite() {
        return white;
    }

    public int getWhiteKing() {
        return whiteKing;
    }

    public int getEmpty() {
        return empty;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
