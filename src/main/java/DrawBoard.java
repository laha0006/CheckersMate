public class DrawBoard {

    public int boardSize;
    public Board board;

    public DrawBoard(Board board) {
        this.board = board;
        boardSize = board.getBoard().length;
    }

    public String moveGuide(){
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

    public String printBoard(){
        StringBuilder sb = new StringBuilder();

        sb.append("+-------------------------+").append("\n| -- ");
        for (int i = 0; i < boardSize; i++) {
            if(i == 3 || i == 11 || i == 19 || i == 27){
                sb.append(stringHelper(board.getBoard()[i])).append(" |").append("\n| ");
            }
            else if(i == 7 || i == 15 || i == 23){
                sb.append(stringHelper(board.getBoard()[i])).append(" -- |").append("\n| -- ");
            }
            else{
                sb.append(stringHelper(board.getBoard()[i])).append(" -- ");
            }
        }
        sb.append("|").append("\n+-------------------------+");


        return sb.toString();
    }

    private String stringHelper(int i){
        //returns an empty spot if there's no piece
        if(i == 0){
            return "  ";
        }
        //if the number is smaller than 10 a space is added
        return (i < 10) ? " "+i : ""+i;
    }
}
