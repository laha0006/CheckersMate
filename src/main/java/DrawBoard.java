import java.util.*;

public class DrawBoard {

    public int boardSize;
    public Board board;

    public DrawBoard(Board board) {
        this.board = board;
        boardSize = board.getBoard().length;
    }

    private final Map<Integer, Integer> JUMP_OVER_INDEX_MAP = MoveMaps.createJumpOverIndexMap();

    public String moveGuide() {
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

    public String printBoard() {
        StringBuilder sb = new StringBuilder();

        sb.append("+-------------------------+").append("\n| -- ");
        for (int i = 0; i < boardSize; i++) {
            if (i == 3 || i == 11 || i == 19 || i == 27) {
                sb.append(stringHelper(board.getBoard()[i])).append(" |").append("\n| ");
            } else if (i == 7 || i == 15 || i == 23) {
                sb.append(stringHelper(board.getBoard()[i])).append(" -- |").append("\n| -- ");
            } else {
                sb.append(stringHelper(board.getBoard()[i])).append(" -- ");
            }
        }
        sb.append("|").append("\n+-------------------------+");

        return sb.toString();
    }

    private String stringHelper(int i) {
        // returns an empty spot if there's no piece
        if (i == 0) {
            return "  ";
        }
        if (i == 1) {
            return ColorWrapper.blue(" B");
        }
        if (i == 11) {
            return ColorWrapper.blue("BB");
        }
        if (i == 2) {
            return ColorWrapper.green(" W");
        }
        if (i == 22) {
            return ColorWrapper.green("WW");
        }
        return null;
    }

    public String printPreviewBoard(String move) {

        StringBuilder sb = new StringBuilder();

        sb.append("+-------------------------+").append("\n| -- ");
        for (int i = 0; i < boardSize; i++) {
            if (i == 3 || i == 11 || i == 19 || i == 27) {
                sb.append(stringPreviewHelper(board.getBoard()[i], i, move)).append(" |").append("\n| ");
            } else if (i == 7 || i == 15 || i == 23) {
                sb.append(stringPreviewHelper(board.getBoard()[i], i, move)).append(" -- |").append("\n| -- ");
            } else {
                sb.append(stringPreviewHelper(board.getBoard()[i], i, move)).append(" -- ");
            }
        }
        sb.append("|").append("\n+-------------------------+");

        return sb.toString();
    }

    private String stringPreviewHelper(int piece, int index, String move) {
        String visual = "";

        if (piece == 0) {
            visual = "  ";
            if (indexIsPartOfMove(index, move)) {
                visual = ColorWrapper.red(" x");
            }

        } else {

            if (indexIsPartOfMove(index, move)) {
                visual = ColorWrapper.red(visual);
            }
            if (piece == 1) {
                if (indexFirstInMove(index, move)) {
                    ColorWrapper.red(" B");
                }
                if (indexIsLastInMove(index, move)) {
                    ColorWrapper.red(" x");
                }
                visual = indexIsPartOfMove(index, move) ? ColorWrapper.red(" B") : ColorWrapper.blue(" B");
            }
            if (piece == 11) {
                if (indexFirstInMove(index, move)) {
                    ColorWrapper.red("BB");
                }
                if (indexIsLastInMove(index, move)) {
                    ColorWrapper.red(" x");
                }
                visual = indexIsPartOfMove(index, move) ? ColorWrapper.red("BB") : ColorWrapper.blue("BB");
            }
            if (piece == 2) {
                if (indexFirstInMove(index, move)) {
                    ColorWrapper.red(" W");
                }
                if (indexIsLastInMove(index, move)) {
                    ColorWrapper.red(" x");
                }
                visual = indexIsPartOfMove(index, move) ? ColorWrapper.red(" W") : ColorWrapper.green(" W");
            }
            if (piece == 22) {
                if (indexFirstInMove(index, move)) {
                    ColorWrapper.red("WW");
                }
                if (indexIsLastInMove(index, move)) {
                    ColorWrapper.red(" x");
                }
                visual = indexIsPartOfMove(index, move) ? ColorWrapper.red("WW") : ColorWrapper.green("WW");
            }

        }
        // if the number is smaller than 10 a space is added

        return visual;
    }

    private List<Integer> parseMoveString(String move) {

        String[] parsedString;
        int moveType;
        if (move.contains("-")) {
            parsedString = move.split("-");
            moveType = 1;
        } else if (move.contains("x")) {
            parsedString = move.split("x");
            moveType = 2;
        } else {
            return null;
        }

        if (moveType == 1) {
            int from = Integer.parseInt(parsedString[0]);
            int to = Integer.parseInt(parsedString[1]);
            return List.of(from, to);
        } else {
            int length = parsedString.length;
            int jumps = length - 1;
            int from;
            int to;
            int jumpedIndex;
            List<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < jumps; i++) {
                from = Integer.parseInt(parsedString[i]);
                to = Integer.parseInt(parsedString[i + 1]);
                jumpedIndex = JUMP_OVER_INDEX_MAP.get(from + to);

                if (i == 0) { // first iteration
                    numbers.add(from);
                }
                numbers.add(jumpedIndex);
                numbers.add(to);
            }

            return numbers;
        }
    }

    private boolean indexIsPartOfMove(int index, String move) {
        return parseMoveString(move).contains(index);
    }

    private boolean indexFirstInMove(int index, String move) {
        return parseMoveString(move).getFirst() == index;
    }

    private boolean indexIsLastInMove(int index, String move) {
        return parseMoveString(move).getLast() == index;
    }


    public String printBoardString() {
        StringBuilder sb = new StringBuilder("board = new int[]{");

        for (int i = 0; i < boardSize; i++) {
            int pieceType = board.getBoard()[i];

            if (pieceType == 0) {
                sb.append("empty");
            }
            if (pieceType == 1) {
                sb.append("blackPawn");
            }
            if (pieceType == 11) {
                sb.append("blackKing");
            }
            if (pieceType == 2) {
                sb.append("whitePawn");
            }
            if (pieceType == 22) {
                sb.append("whiteKing");
            }
            if (i != boardSize-1) {
                sb.append(",");
            }    
        }
        sb.append("};");
        return sb.toString();
    }
}
