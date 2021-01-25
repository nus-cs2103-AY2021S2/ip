import java.util.ArrayList;

public class Ui {

    Ui() {

    }

    public static void fileError() {
        System.out.println("Some errors were encountered with the file");
    }

    public static void addedTask(ArrayList<Task> taskList) {
        // Retrieve last task in list and print it
        System.out.println("Added to list:\n" + taskList.get(taskList.size() - 1).toString());
        System.out.println("You now have " + taskList.size() + " tasks\n");
    }

    public static void removeTask(ArrayList<Task> taskList) {
        System.out.println("Got it! I've removed the task for you\n");
        System.out.println("You now have " + taskList.size() + " tasks\n");
    }

    public static void completedTask() {
        System.out.println("Good job! I've marked it as completed for you as well!\n");
    }

    public static void fileNotFound() {
        System.out.println("I notice this is the first time you're running the script!");
    }

    public static void init() {
        System.out.println("Hello! I'm SwitchBlade and I aim to do everything you want me to do!");
    }

    public static void initRetrieveList(myList taskList) {
        if (taskList.getNumTasks() > 0) {
            System.out.println("It seems like you've had a task list from a previous session, here's how it looks"
                            + taskList.toString());
        }
    }

    public static void printList(myList taskList) {
        System.out.println("Here's your list of tasks!" + taskList.toString());
    }

    public static void shutdown() {
        System.out.println("See you soon!");
    }
}
