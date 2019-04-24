package main.java.com.methods;

/**
 * Colorful text by SergeyDorohin
 * @author SergeyDorohin
 * @version 1
 */

public class ColorfulText {

    // text:
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    // background:
    private static final String RESET_BACKGROUND = "\u001B[40m";
    private static final String RED_BACKGROUND = "\u001B[41m";
    private static final String GREEN_BACKGROUND = "\u001B[42m";
    private static final String YELLOW_BACKGROUND = "\u001B[43m";
    private static final String BLUE_BACKGROUND = "\u001B[44m";
    private static final String PURPLE_BACKGROUND = "\u001B[45m";
    private static final String CYAN_BACKGROUND = "\u001B[46m";
    private static final String WHITE_BACKGROUND = "\u001B[47m";

    // bold:
    private static final String plainText = "\033[0;0m";
    private static final String boldText = "\033[0;1m";

    public static void setBackground(String background) {
        log(switchBackground(background));
    }

    public static void terminal(String info) {
        constructor("yellow", "[server] > ");
        constructor("red", info);
        constructor("yellow", " <");
        logln();
    }

    public static void client(String info) {
        log(GREEN + "Answer from server: " + RESET + YELLOW + boldText + info + plainText);
    }

    public static String setBold() {
        return (boldText);
    }

    public static String setPlain() {
        return (plainText);
    }

    public static void constructor(String color, String text) {
        log(getColor(color) + text + reset());
    }

    public static String getColor(String color) {
        return (switchColor(color));
    }

    public static String reset() {
        return RESET;
    }

    public static void log(String message) {
        System.out.print(message);
    }

    public static void logln() {
        System.out.println("");
    }

    private static String switchColor(String color) {
        color = color.toLowerCase();
        switch (color){
            case ("black"):
                return BLACK;
            case ("red"):
                return RED;
            case ("green"):
                return GREEN;
            case ("yellow"):
                return YELLOW;
            case ("blue"):
                return BLUE;
            case ("purple"):
                return PURPLE;
            case ("cyan"):
                return CYAN;
            case ("white"):
                return WHITE;
            default:
                return RESET;
        }
    }

    private static String switchBackground(String background) {
        background = background.toLowerCase();
        switch (background) {
            case ("red"):
                return RED_BACKGROUND;
            case ("green"):
                return GREEN_BACKGROUND;
            case ("yellow"):
                return YELLOW_BACKGROUND;
            case ("blue"):
                return BLUE_BACKGROUND;
            case ("purple"):
                return PURPLE_BACKGROUND;
            case ("cyan"):
                return CYAN_BACKGROUND;
            case ("white"):
                return WHITE_BACKGROUND;
            default:
                return RESET_BACKGROUND;
        }
    }
}