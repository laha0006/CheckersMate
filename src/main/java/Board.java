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

    public static String moveGuide(){
        return """
                +-------------------------+
                | -- 00 -- 01 -- 02 -- 03 |
                | 04 -- 05 -- 06 -- 07 -- |
                | -- 08 -- 09 -- 10 -- 11 |
                | 12 -- 13 -- 14 -- 15 -- |
                | -- 16 -- 17 -- 18 -- 19 |
                | 20 -- 21 -- 22 -- 23 -- |
                | -- 24 -- 25 -- 26 -- 27 |
                | 28 -- 29 -- 30 -- 31 -- |
                +-------------------------+
                """;
    }

    public static String printBoard(){
        StringBuilder string = new StringBuilder();

        string.append("+-------------------------+");
        string.append("| -- "+);

        return string;
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
