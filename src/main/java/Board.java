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

    public static String printBoard(int[] b){
        String string = "+---------------------+";
        string += "\n| -- "+b[0]+" -- "+b[1]+" -- "+b[2]+" -- "+b[3]+" |";
        string += "\n| "+b[4]+" -- "+b[5]+" -- "+b[6]+" -- "+b[7]+" -- |";
        string += "\n| -- "+b[8]+" -- "+b[9]+" -- "+b[10]+" -- "+b[11]+" |";
        string += "\n| "+b[12]+" -- "+b[13]+" -- "+b[14]+" -- "+b[15]+" -- |";
        string += "\n| -- "+b[16]+" -- "+b[17]+" -- "+b[18]+" -- "+b[19]+" |";
        string += "\n| "+b[20]+" -- "+b[21]+" -- "+b[22]+" -- "+b[23]+" -- |";
        string += "\n| -- "+b[24]+" -- "+b[25]+" -- "+b[26]+" -- "+b[27]+" |";
        string += "\n| "+b[28]+" -- "+b[29]+" -- "+b[30]+" -- "+b[31]+" -- |";
        string += "\n+---------------------+";

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
