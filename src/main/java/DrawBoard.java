import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if (i == 1)
            return ColorWrapper.black(" B");
        if (i == 11)
            return ColorWrapper.black("BB");
        if (i == 2)
            return ColorWrapper.cyan(" W");
        if (i == 22)
            return ColorWrapper.cyan("WW");
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
        // returns an empty spot if there's no piece
        if (piece == 0) {
            visual = "  ";
            if (parseMoveString(move).contains(index)) {
                visual = ColorWrapper.red(" x");
            }
        } else {
            if (piece == 1)
                visual = ColorWrapper.black(" B");
            if (piece == 11)
                visual = ColorWrapper.black("BB");
            if (piece == 2)
                visual = ColorWrapper.cyan(" W");
            if (piece == 22)
                visual = ColorWrapper.cyan("WW");
            if (parseMoveString(move).contains(index)) {
                visual = ColorWrapper.red(visual);
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

        int length = parsedString.length;

        if (moveType == 1) {
            int from = Integer.parseInt(parsedString[0]);
            int to = Integer.parseInt(parsedString[1]);
            return List.of(from, to);
        } else {
            int jumps = length - 1;
            int from;
            int to;
            List<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < jumps; i++) {
                from = Integer.parseInt(parsedString[i]);
                to = Integer.parseInt(parsedString[i + 1]);
                int jumpedIndex = JUMP_OVER_INDEX_MAP.get(from + to);
                numbers.add(from);
                numbers.add(to);
                numbers.add(jumpedIndex);
            }

            return numbers;
        }
    }

}
