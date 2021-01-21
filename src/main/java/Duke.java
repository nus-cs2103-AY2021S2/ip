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
        ArrayList<AbstractTask> tasks = new ArrayList<>();
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
                for(int i = 0;i < tasks.size();i++) {
                    System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
                }
            } else if (line.startsWith("done ")) {
                // Done command, set the task as done.
                String indexStr = line.substring(5);
                int index = Integer.parseInt(indexStr) - 1;
                AbstractTask currentTask = tasks.get(index);
                currentTask.markDone();
                System.out.printf("\tDuke: Marked task %d as done:\n", index);
                System.out.printf("\t%s\n", currentTask);
            } else {
                // No command, add the line task based on the prefix inside.
                if (line.startsWith("todo ")) {
                    // Todo command, add a Todo class
                    line = line.substring(5);
                    tasks.add(new Todo(line));
                } else if (line.startsWith("deadline ")) {
                    // Deadline command, add a Deadline class
                    int byIdx = line.indexOf(" /by ");
                    String byStr = line.substring(byIdx + 5);
                    String task = line.substring(9, byIdx);
                    tasks.add(new Deadline(task, byStr));
                } else if (line.startsWith("event ")) {
                    // Evebt command, add an Event class
                    int atIdx = line.indexOf(" /at ");
                    String atStr = line.substring(atIdx + 5);
                    String task = line.substring(6, atIdx);
                    tasks.add(new Event(task, atStr));
                } else {
                    tasks.add(new Task(line));
                }
                System.out.printf("\tDuke: Now you have %d tasks.\n", tasks.size());
                System.out.println("\tadded: " + tasks.get(tasks.size() - 1));
            }
        }
    }
}
