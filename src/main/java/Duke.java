import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static List<String> database = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Prints greeting
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        pw.printf("Hello! I'm \n%s\nWhat can I do for you?\n", logo);
        printHorizontalLine();
        pw.flush();

        // Ask for commands
        boolean isExiting = false;
        while (true) {
            String command = br.readLine();
            printHorizontalLine();
            switch (command) {
                case "list":
                    if (database.isEmpty()) {
                        pw.println("You do not have anything to do at the moment!");
                    }
                    for (int i = 1; i <= database.size(); i++) {
                        pw.printf("%d. %s\n", i, database.get(i - 1));
                    }
                    break;
                case "bye":
                    isExiting = true;
                    break;
                default:
                    database.add(command);
                    printAddedTask(command);
            }
            if (isExiting) {
                break;
            }
            printHorizontalLine();
            pw.flush();
        }

        pw.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        pw.close();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            pw.print('-');
        }
        pw.println();
    }

    public static void printAddedTask(String str) {
        pw.printf("Added task: %s\n", str);
    }
}
