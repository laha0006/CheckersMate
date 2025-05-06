public class AI {

    public final int black = 1;
    public final int blackPawn = 1;
    public final int blackKing = 11;

    public final int white = 2;
    public final int whitePawn = 2;
    public final int whiteKing = 22;

    public final int empty = 0;

    Board board;
    int computer;
    public AI(Board board, int computer) {
        this.board = board;
        this.computer = computer;
    }

    public String getComputerMove() {
        return "2x2";
    }

    public String minMax(boolean isMax, int alpha, int beta, int currentDepth, int maxDepth) {
        if(currentDepth == maxDepth) {
            return
        }
    }

    public int evaulate() {
        int computerCount = 0;
        int playerCount = 0;
        int[] theBoard = board.getBoard();
        for(int i = 0; i < theBoard.length; i++) {
            int piece = theBoard[i];
            if(piece == blackPawn || piece == blackKing) {
                if(computer == black) {
                    computerCount++;
                } else {
                    playerCount++;
                }
            }
            if(piece == whitePawn || piece == whiteKing) {
                if(computer == white) {
                    computerCount++;
                } else {
                    playerCount++;
                }
            }
        }
        return computerCount-playerCount;
    }
}
