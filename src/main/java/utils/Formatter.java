package utils;

public class Formatter {

    public static final String INDENTATION = "    ";

    public static void printlnWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
    }

    public static void printHorizontalLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
    }

    public static void printBetweenLines(String... strings) {
        printHorizontalLine();

        for (int i = 0; i < strings.length; i++) {
            printlnWithIndentation(strings[i]);
        }

        printHorizontalLine();
    }
}
