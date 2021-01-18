import java.util.Scanner;

public class Duke {
    public static String BOT_NAME = "Apollo the Robot!";
    public static String INDENTATION = "    ";
    public static String logo = INDENTATION
            + "     \\    ___\\   ___\\  \\     \\       ___\n"
            + INDENTATION
            + "   /  \\  |    |/     \\ |     |     /     \\ \n"
            + INDENTATION
            + "  ------ |___/ |      ||     |    |       |\n"
            + INDENTATION
            + " /      \\|     \\     / |     |     \\     / \n"
            + INDENTATION
            + "/        |      \\___/  \\_____\\_____ \\___/ \n";

    public static void printlnWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
    }

    public static void printHorizontalLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
    }

    public static void main(String[] args) {
        printHorizontalLine();
        printlnWithIndentation("Hello from " + BOT_NAME + "\n" + logo);
        printlnWithIndentation("What would you like to do today?\n");
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
