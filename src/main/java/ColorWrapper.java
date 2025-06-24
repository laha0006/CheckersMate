

public class ColorWrapper {


    public enum Color {
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m"),
        RESET("\u001B[0m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

    }


    private String text = "";
    private Color color = Color.RESET;

    public void prepare(String text, Color color) {
        this.text = text;
        this.color = color;
    }
    public void prepare(String text) {
        this.text = text;
    }
    public void prepare(Color color) {
        this.color = color;
    }


    public String execute() {
        String coloredText = color.code + text + Color.RESET.code;
        reset();
        return coloredText;
    }

    private void reset() {
        text = "";
        color = Color.RESET;
    }

}
