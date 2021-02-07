import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class that takes in input and performs certain functions based on input.
 */
public class Duke {

    protected static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Main class to take in user input.
     * @param args Filler
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String command = "Hello";

        // Creating directory if it does not exist
        File directory = new File("data");
        if (!directory.exists()) {
            boolean success = directory.mkdir();
            if (!success) {
                System.out.println("Directory creation was unsuccessful. Please " +
                        "manually create it.");
            }
        }

        while (!command.equalsIgnoreCase("bye")) {
            if (command.equals("Hello")) {
                System.out.println("Hello! I'm Duke");
                System.out.println("What can I do for you?");
            } else if (command.equals("list")) {
                enumerateTasks();
            } else if (command.startsWith("done")) {
                String[] delString = command.split("\\s+");
                markAsDone(Integer.parseInt(delString[1]));
            } else if (command.startsWith("todo")) {
                try {
                    Todo currentTask = new Todo(command.substring(5));
                    addToTasks(currentTask);
                    logTask(currentTask);
                } catch (StringIndexOutOfBoundsException indexError) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (command.startsWith("event")) {
                try {
                    String[] splitString = command.split("/at");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Event currentTask = new Event(eventDesc.substring(6), eventDate);
                    addToTasks(currentTask);
                    logTask(currentTask);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    System.out.println("☹ OOPS!!! The description of an event cannot " +
                            "be empty.");
                }
            } else if (command.startsWith("deadline")) {
                try {
                    String[] splitString = command.split("/by");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Deadline currentTask = new Deadline(eventDesc.substring(9), eventDate);
                    addToTasks(currentTask);
                    logTask(currentTask);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    System.out.println("☹ OOPS!!! The description of a deadline " +
                            "cannot be empty.");
                }
            } else if (command.startsWith("delete")) {
                String[] splitString = command.split("\\s+");
                removeTask(Integer.parseInt(splitString[1]));
            } else {
                // Command is not recognized
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            command = sc.nextLine();

        }

        BufferedWriter out = new BufferedWriter(new FileWriter("data/Duke.txt",
                false));
        out.write(logAllTasks());
        out.close();
        System.out.println("Bye. Hope to see you again soon!");

    }

    /**
     * Prints the statements showing this task has been added to list.
     * @param currentTask Current task.
     */
    protected static void logTask(Task currentTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(currentTask);
        System.out.println(String.format("Now you have %d tasks in the list"
                , taskList.size()));
    }

    /**
     * Adds current task to list of tasks.
     * @param currentTask Current task.
     */
    protected static void addToTasks(Task currentTask) {
        taskList.add(currentTask);
    }

    /**
     * Enumerates all tasks in the lis using 1-based indexing.
     */
    protected static void enumerateTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task eachTask : taskList) {
            System.out.println(String.format("%d. %s", counter, eachTask));
            counter++;
        }
    }

    protected static String logAllTasks() {
        StringBuilder out = new StringBuilder();
        for (Task eachTask : taskList) {
            out.append(eachTask + "\n");
        }
        return out.toString();
    }

    /**
     * Marks task as done based on 1-based indexing.
     * @param index Given index of task
     */
    protected static void markAsDone(int index) {
        // Retrieving task
        Task givenTask = taskList.get(index - 1);
        givenTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  [%s][%s] %s",
                givenTask.getTaskType(), givenTask.getStatusIcon(),
                givenTask.getDescription()));
    }

    /**
     * Removes respective task in the list (1-based indexing).
     * @param index Index of task to remove
     */
    protected static void removeTask(int index) {
        Task removedTask = taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println(String.format("Now you have %d tasks in your list.",
                taskList.size()));
    }

}
