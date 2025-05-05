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
                | --  0 --  1 --  2 --  3 |
                |  4 --  5 --  6 --  7 -- |
                | --  8 --  9 -- 10 -- 11 |
                | 12 -- 13 -- 14 -- 15 -- |
                | -- 16 -- 17 -- 18 -- 19 |
                | 20 -- 21 -- 22 -- 23 -- |
                | -- 24 -- 25 -- 26 -- 27 |
                | 28 -- 29 -- 30 -- 31 -- |
                +-------------------------+
                """;
    }

    public static String printBoard(int[] b){
        //this wont work for formatting if normal pieces are 1 digit and kings are 2
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


        //introducing the really unnecessarily-long way of doing it with digit size differences
        StringBuilder sb = new StringBuilder();

        sb.append("+-------------------------+")
                .append("\n| -- ")
                .append(stringHelper(b[0])).append(" -- ")
                .append(stringHelper(b[1])).append(" -- ")
                .append(stringHelper(b[2])).append(" -- ")
                .append(stringHelper(b[3])).append(" |")
                .append("\n| ")
                .append(stringHelper(b[4])).append(" -- ")
                .append(stringHelper(b[5])).append(" -- ")
                .append(stringHelper(b[6])).append(" -- ")
                .append(stringHelper(b[7])).append(" -- |")
                .append("\n| -- ")
                .append(stringHelper(b[8])).append(" -- ")
                .append(stringHelper(b[9])).append(" -- ")
                .append(stringHelper(b[10])).append(" -- ")
                .append(stringHelper(b[11])).append(" |")
                .append("\n| ")
                .append(stringHelper(b[12])).append(" -- ")
                .append(stringHelper(b[13])).append(" -- ")
                .append(stringHelper(b[14])).append(" -- ")
                .append(stringHelper(b[15])).append(" -- |")
                .append("\n| -- ")
                .append(stringHelper(b[16])).append(" -- ")
                .append(stringHelper(b[17])).append(" -- ")
                .append(stringHelper(b[18])).append(" -- ")
                .append(stringHelper(b[19])).append(" |")
                .append("\n| ")
                .append(stringHelper(b[20])).append(" -- ")
                .append(stringHelper(b[21])).append(" -- ")
                .append(stringHelper(b[22])).append(" -- ")
                .append(stringHelper(b[23])).append(" -- |")
                .append("\n| -- ")
                .append(stringHelper(b[24])).append(" -- ")
                .append(stringHelper(b[25])).append(" -- ")
                .append(stringHelper(b[26])).append(" -- ")
                .append(stringHelper(b[27])).append(" |")
                .append("\n| ")
                .append(stringHelper(b[28])).append(" -- ")
                .append(stringHelper(b[29])).append(" -- ")
                .append(stringHelper(b[30])).append(" -- ")
                .append(stringHelper(b[31])).append(" -- |")
                .append("\n+-------------------------+");

        return sb.toString();
    }

    private static String stringHelper(int i){
        //if the number is smaller than 10 a space is added
        return (i < 10) ? " "+i : ""+i;
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
