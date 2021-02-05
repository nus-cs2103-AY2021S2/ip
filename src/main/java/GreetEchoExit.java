import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class that takes in input and performs certain functions based on input
 */
public class GreetEchoExit {

    protected static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Main class to take in user input
     * @param args Filler
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String command = "Hello";

        while(!command.equals("bye")) {
            if (command.equals("Hello")) {
                System.out.println("Hello! I'm Duke");
                System.out.println("What can I do for you?");
            } else if (command.equals("list")) {
                enumerateTasks();
            } else if (command.contains("done")) {
                String[] delString = command.split("\\s+");
                markAsDone(Integer.parseInt(delString[1]));
            } else {
                System.out.println(String.format("added: %s", command));
            }
            command = sc.nextLine();
            if (!command.equals("list") && !command.equals("done")) {
                taskList.add(new Task(command));
            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    protected static void enumerateTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task eachTask : taskList) {
            System.out.println(String.format("%d.[%s] %s", counter,
                    eachTask.getStatusIcon(), eachTask.getDescription()));
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
        System.out.println(String.format("  [%s] %s", givenTask.getStatusIcon(),
                givenTask.getDescription()));
    }

}
