import java.util.Scanner;

public class Duke {
    public static void printLine(String line) {
        System.out.println("     " + line);
    }

    public static void printHorizontalLine() {
        System.out.println("    " + "____________________________________________________________");
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static boolean processCommand(String command) {
        printHorizontalLine();
        if (command.equals("bye")) {
            printLine("Bye. Hope to see you again soon!");
        } else {
            printLine(command);
        }
        printHorizontalLine();
        printEmptyLine();
        if (command.equals("bye")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "██████╗ ██╗   ██╗██╗  ██╗███████╗\n" +
                "██╔══██╗██║   ██║██║ ██╔╝██╔════╝\n" +
                "██║  ██║██║   ██║█████╔╝ █████╗  \n" +
                "██║  ██║██║   ██║██╔═██╗ ██╔══╝  \n" +
                "██████╔╝╚██████╔╝██║  ██╗███████╗\n" +
                "╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
        printHorizontalLine();
        printEmptyLine();
        for (String line : logo.split("\n")) {
            printLine(line);
        }
        printEmptyLine();
        printLine("Hello! I'm Duke");
        printLine("What can I do for you?");
        printHorizontalLine();
        printEmptyLine();
        while (processCommand(sc.nextLine())) ;
    }
}
