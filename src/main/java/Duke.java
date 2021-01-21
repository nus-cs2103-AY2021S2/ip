import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static ArrayList<AbstractTask> tasks = new ArrayList<>();

    /*
     * Main class to handle the input
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println("What can I do for you?");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            processLine(line);
        }
    }

    /*
     * Handle each line
     */
    public static void processLine(String line) {
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
            try {
                int index = Integer.parseInt(indexStr) - 1;
                AbstractTask currentTask = tasks.get(index);
                currentTask.markDone();
                System.out.printf("\tDuke: Marked task %d as done:\n", index);
                System.out.printf("\t%s\n", currentTask);
            } catch (NumberFormatException e){
                System.out.println("Task index must be a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task index must be in range!");
            }
        } else if (line.startsWith("delete ")) {
            // Done command, set the task as done.
            String indexStr = line.substring(5);
            try {
                int index = Integer.parseInt(indexStr) - 1;
                tasks.remove(index);
            } catch (NumberFormatException e){
                System.out.println("Task index must be a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task index must be in range!");
            }
        } else {
            // No command, add the line task based on the prefix inside.
            try {
                processNewTask(line);
            } catch (DukeUnknownCommandException e) {
                //Handle Unknown Command Exception
                System.out.println("Unknown command detected, ignoring!");
            } catch (DukeEmptyDescriptionException e) {
                //Handle Empty Description Exception
                System.out.println("Task description cannot be empty, ignoring!");
            } catch (DukeException e) {
                System.out.println("Reached an error!");
            }
        }
    }

    /*
     * Handle a new task
     */
    public static void processNewTask(String line) throws DukeException {
        if (line.startsWith("todo ")) {
            // Todo command, add a Todo class
            line = line.substring(5);
            tasks.add(new Todo(line));
        } else if (line.startsWith("deadline ")) {
            // Deadline command, add a Deadline class
            int byIdx = line.indexOf(" /by ");
            if (byIdx == -1) {
                System.out.println("Need a time for the deadline, \"/by\" not found!");
                return;
            }
            String byStr = line.substring(byIdx + 5);
            String task = line.substring(9, byIdx);
            tasks.add(new Deadline(task, byStr));
        } else if (line.startsWith("event ")) {
            // Event command, add an Event class
            int atIdx = line.indexOf(" /at ");
            if (atIdx == -1) {
                System.out.println("Need a time for the event, \"/at\" not found!");
                return;
            }
            String atStr = line.substring(atIdx + 5);
            String task = line.substring(6, atIdx);
            tasks.add(new Event(task, atStr));
        } else if (line.equals("todo") || line.equals("deadline") || line.equals("event")) {
            throw new DukeEmptyDescriptionException();
        } else {
            throw new DukeUnknownCommandException();
        }
        System.out.printf("\tDuke: Now you have %d tasks.\n", tasks.size());
        System.out.println("\tadded: " + tasks.get(tasks.size() - 1));
    }
}
