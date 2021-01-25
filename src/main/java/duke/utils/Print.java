package duke.utils;

public class Print {
    private static final String LINE_BREAK = "------------------------------------------------------------";
    private static final String INDENT = "    ";

    public static void printWithIndentation(String ... strings) {
        System.out.println(INDENT + LINE_BREAK);

        for (String s : strings) {
            System.out.println(INDENT + s);
        }

        System.out.println(INDENT + LINE_BREAK);
    }
}
