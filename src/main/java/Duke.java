import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /*
     * Main class to handle the input
     */
    public static void main(String[] args) {
        ArrayList<Task> lines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println("What can I do for you?");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.compareTo("bye") == 0) {
                // Bye command, print and exit immediately.
                System.out.println("\tDuke:");
                System.out.println("\tBye!");
                return;
            } else if (line.compareTo("list") == 0) {
                // List command, print out all the previous lines.
                System.out.println("\tDuke:");
                for(int i = 0;i < lines.size();i++) {
                    System.out.printf("\t%d. %s\n", i + 1, lines.get(i));
                }
            } else if (line.startsWith("done ")) {
                // Done command, set the task as done.
                String indexStr = line.substring(5);
                int index = Integer.parseInt(indexStr) - 1;
                Task currentTask = lines.get(index);
                currentTask.markDone();
                System.out.printf("\tDuke: Marked task %d as done:\n", index);
                System.out.printf("\t%s\n", currentTask);
            } else {
                // No command, add the line inside.
                lines.add(new Task(line));
                System.out.println("\tDuke:");
                System.out.println("\tadded: " + line);
            }
        }
    }
}
