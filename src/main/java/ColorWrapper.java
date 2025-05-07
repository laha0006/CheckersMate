public class ColorWrapper {
    // ANSI escape codes
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static String blue(String text) {
        return BLUE + text + RESET;
    }

    public static String red(String text) {
        return RED + text + RESET;
    }
}
