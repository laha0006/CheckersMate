import java.util.ArrayList;
import java.util.List;

public class AI {

    public final int black = 1;
    public final int blackPawn = 1;
    public final int blackKing = 11;

    public final int white = 2;
    public final int whitePawn = 2;
    public final int whiteKing = 22;

    public final int empty = 0;
    Engine engine;

    int computer;

    public AI(Engine engine, int computer) {
        this.computer = computer;
        this.engine = engine;
    }

    public String getComputerMove() {
        MinMax aiMove = minMax(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 8);
        System.out.println(aiMove);
        return aiMove.move();
    }

    public MinMax minMax(boolean isMax, int alpha, int beta, int currentDepth, int maxDepth) {
        MinMax best;
        List<String> moves = engine.getMovesForTurn();

        if (currentDepth == maxDepth || isMovesEmpty(moves)) {
            return new MinMax(evaluate(), "");
        }


        if (isMax) {
            best = new MinMax(Integer.MIN_VALUE, "");
            for (String move : moves) {
                MinMax result = performVirtualMove(move, false, alpha, beta, currentDepth, maxDepth);
                if (result.score() > best.score()) {
                    best = new MinMax(result.score(), move);
                }
                if (best.score() >= beta) {
                    break;
                }
                alpha = Math.max(alpha, best.score());
            }
        } else {
            best = new MinMax(Integer.MAX_VALUE, "");
            for (String move : moves) {
                MinMax result = performVirtualMove(move, true , alpha, beta, currentDepth, maxDepth);
                if (result.score() < best.score()) {
                    best = new MinMax(result.score(), move);
                }
                if (best.score() <= alpha) {
                    break;
                }
                beta = Math.min(beta, best.score());
            }

        }
        return best;
    }

    public int evaluate() {
        int computerCount = 0;
        int playerCount = 0;
        int[] theBoard = engine.getState().getBoard();
        List<String> moves = engine.getMovesForTurn();
        int turn = engine.getTurn();

        // Determine which color is the computer and which is the player
        int computerColor = computer;
        int playerColor = (computer == black) ? white : black;

        if (moves.isEmpty() && turn == computerColor) {
            return -1000;
        }
        if (moves.isEmpty() && turn == playerColor) {
            return 1000;
        }

        for (int piece : theBoard) {
            if (engine.isBlack(piece) || engine.isWhite(piece)) {
                int value = engine.isKing(piece) ? 2 : 1;
                if ((engine.isBlack(piece) && computerColor == black) ||
                    (engine.isWhite(piece) && computerColor == white)) {
                    computerCount += value;
                } else {
                    playerCount += value;
                }
            }
        }

        if(playerCount == 0) {
            return 1000;
        }
        if(computerCount == 0) {
            return -1000;
        }

        return computerCount - playerCount;
    }

    public MinMax performVirtualMove(String move, boolean isMax, int alpha, int beta, int currentDepth, int maxDepth) {
        Board state = engine.getState();
        state.save();
        engine.playerMove(move);
        MinMax result = minMax(isMax, alpha, beta, currentDepth + 1, maxDepth);
        state.load();
        engine.flipTurn();
        return result;
    }

    public boolean isMovesEmpty(List<String> moves) {
        return moves.isEmpty();
    }

    public boolean winCondition(){
        /*
        Board state = engine.getState();
        for (int i = 0; i < state.boardSize; i++) {

        }
        11
         */
        return false;
    }
}
