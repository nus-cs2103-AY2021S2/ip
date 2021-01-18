import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    private static void printHorizontalLine() {
        printIndentOutput("_____________________________________________________");
    }

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printHorizontalLine();
        printIndentOutput("What can I do for you?");
        printHorizontalLine();

        Scanner stdin = new Scanner(System.in);
        String line = stdin.nextLine();
        boolean end = false;

        while (line != null) {
            printHorizontalLine();

            if (line.equals("bye")) {
                printIndentOutput("Bye. Hope to see you again soon!");
                end = true;
            } else if (line.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    printIndentOutput((i + 1) + ": " + tasks.get(i));
                }
            } else {
                tasks.add(line);
                printIndentOutput("added: " + line);
            }

            printHorizontalLine();

            if (end) break;

            line = stdin.nextLine();
        }
    }
}
