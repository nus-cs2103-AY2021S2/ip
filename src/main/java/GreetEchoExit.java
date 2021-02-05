import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class that takes in input and performs certain functions based on input.
 */
public class GreetEchoExit {

    protected static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Main class to take in user input.
     * @param args Filler
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String command = "Hello";

        while(!command.equalsIgnoreCase("bye")) {
            if (command.equals("Hello")) {
                System.out.println("Hello! I'm Duke");
                System.out.println("What can I do for you?");
            } else if (command.equals("list")) {
                enumerateTasks();
            } else if (command.contains("done")) {
                String[] delString = command.split("\\s+");
                markAsDone(Integer.parseInt(delString[1]));
            } else if (command.contains("todo")) {
                try {
                    Todo currentTask = new Todo(command.substring(5));
                    taskList.add(currentTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currentTask);
                    System.out.println(String.format("Now you have %d tasks in the list"
                            , taskList.size()));
                } catch (StringIndexOutOfBoundsException indexError) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (command.contains("event")) {
                try {
                    String[] splitString = command.split("/at");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Event currentTask = new Event(eventDesc.substring(6), eventDate);
                    taskList.add(currentTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currentTask);
                    System.out.println(String.format("Now you have %d tasks in the list"
                            , taskList.size()));
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    System.out.println("☹ OOPS!!! The description of an event cannot " +
                            "be empty.");
                }
            } else if (command.contains("deadline")) {
                try {
                    String[] splitString = command.split("/by");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Deadline currentTask = new Deadline(eventDesc.substring(9), eventDate);
                    taskList.add(currentTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currentTask);
                    System.out.println(String.format("Now you have %d tasks in the list"
                            , taskList.size()));
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    System.out.println("☹ OOPS!!! The description of a deadline " +
                            "cannot be empty.");
                }
            } else {
                // Command is not recognized
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            
            command = sc.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    /**
     * Enumerates all tasks in the lis using 1-based indexing.
     */
    protected static void enumerateTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task eachTask : taskList) {
            System.out.println(eachTask);
            counter++;
        }
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

}
