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
        MinMax aiMove = minMax(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 6);
        System.out.println(aiMove);
        return aiMove.move();
    }

    public MinMax minMax(boolean isMax, int alpha, int beta, int currentDepth, int maxDepth) {
        if (currentDepth == maxDepth) {
            return new MinMax(evaulate(), "");
        }

        Board state = engine.getState();
        MinMax best;
        if (isMax) {
            best = new MinMax(Integer.MIN_VALUE, "");
//            if (computer != engine.getTurn()) {
//                System.out.println("FLIPPED TURN");
//                engine.flipTurn();
//            }
            List<String> moves = engine.getMovesForTurn();
            for (String move : moves) {
                state.save();
                engine.playerMove(move);
                MinMax result = minMax(false, alpha, beta, currentDepth + 1, maxDepth);
                state.load();
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
//            if (computer == engine.getTurn())
//                engine.flipTurn();
            List<String> moves = engine.getMovesForTurn();
            for (String move : moves) {
                state.save();
                engine.playerMove(move);
//                engine.flipTurn();
                MinMax result = minMax(true, alpha, beta, currentDepth + 1, maxDepth);
                state.load();
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

    public int evaulate() {
        int computerCount = 0;
        int playerCount = 0;
        int[] theBoard = engine.getState().getBoard();
        for (int i = 0; i < theBoard.length; i++) {
            int piece = theBoard[i];
            if (engine.isBlack(piece)) {
                if (computer == black) {
                     if (engine.isKing(piece)) {
                         computerCount += 10;
                     } else {
                         computerCount++;
                     }
                } else {
                     if (engine.isKing(piece)) {
                         playerCount += 10;
                     } else {
                         playerCount++;
                     }

                }
            }
            if (engine.isWhite(piece)) {
                if (computer == white) {
                     if (engine.isKing(piece)) {
                         computerCount += 10;
                     } else {
                         computerCount++;
                     }
                } else {
                     if (engine.isKing(piece)) {
                         playerCount += 10;
                     } else {
                         playerCount++;
                     }
                }
            }
        }
        return computerCount - playerCount;
    }
}
