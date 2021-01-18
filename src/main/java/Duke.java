import java.util.Scanner;

public class Duke {
    public static String BOT_NAME = "Apollo the Robot!";
    public static String INDENTATION = "    ";

    public static void printlnWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
    }

    public static void printHorizontalLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
    }

    public static void main(String[] args) {
        printHorizontalLine();
        printlnWithIndentation("Hello! I'm " + BOT_NAME + "!");
        printlnWithIndentation("What would you like to do today?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String command = scanner.next();

            if (command.equals("bye")) {
                printHorizontalLine();
                printlnWithIndentation("Bye. Hope to see you again soon!");
                printHorizontalLine();
                System.exit(0);
            } else {
                printHorizontalLine();
                printlnWithIndentation(command);
                printHorizontalLine();
            }
        }
    }
}
