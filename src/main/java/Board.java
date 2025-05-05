public class Board {
    //white pieces
    public static final int black = 1;
    public static final int blackPawn = 1;
    public static final int blackKing = 11;
    //black pieces
    public static final int white = 2;
    public static final int whitePawn = 2;
    public static final int whiteKing = 22;
    //empty spots on the board
    public static final int empty = 0;
    //board size (this should not change)
    public static final int boardSize = 32;

    public static int[] createStartBoard() {
        int[] boardState = new int[boardSize];
        for (int i = 0; i < 12; i++) {
            boardState[i] = blackPawn;
        }
        for (int i = 20; i < 32;  i++  ) {
            boardState[i] = whitePawn;
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
        StringBuilder sb = new StringBuilder();

        sb.append("+-------------------------+").append("\n| -- ");
        for (int i = 0; i < boardSize; i++) {
            if(i == 3 || i == 11 || i == 19 || i == 27){
                sb.append(stringHelper(b[i])).append(" |").append("\n| ");
            }
            else if(i == 7 || i == 15 || i == 23){
                sb.append(stringHelper(b[i])).append(" -- |").append("\n| -- ");
            }
            else{
                sb.append(stringHelper(b[i])).append(" -- ");
            }
        }
        sb.append("|").append("\n+-------------------------+");


        return sb.toString();
    }

    private static String stringHelper(int i){
        //returns an empty spot if there's no piece
        if(i == 0){
            return "  ";
        }
        //if the number is smaller than 10 a space is added
        return (i < 10) ? " "+i : ""+i;
    }

    
}
