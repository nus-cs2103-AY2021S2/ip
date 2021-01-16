import java.util.Scanner;

public class Duke {
    private static String LINE_BREAK = "------------------------------------------------------------";
    private static String BOT_NAME = "Chip the Squirrel";

    public static void printWithIndentation(String s) {
        String indent = "    ";
        System.out.println(indent + s);
    }

    public static void main(String[] args) {
        printWithIndentation(LINE_BREAK);

        printWithIndentation("Hello! I'm " + BOT_NAME + "!");
        printWithIndentation("What can I do for you today?");
        printWithIndentation(LINE_BREAK);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.next();

            switch(command) {
                case "bye":
                    printWithIndentation(LINE_BREAK);
                    printWithIndentation("Bye! Hope to see you again soon!");
                    printWithIndentation(LINE_BREAK);
                    System.exit(0);
                    break;
                default:
                    printWithIndentation(LINE_BREAK);
                    printWithIndentation(command);
                    printWithIndentation(LINE_BREAK);
            }

        }
    }
}
