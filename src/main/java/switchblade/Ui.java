package switchblade;

import java.util.ArrayList;

/**
 * Handles any printing required to the user such that no other classes should print to user
 *
 * @author leeyueyang
 */
public class Ui {

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
        System.out.println("Hello! I'm switchblade.SwitchBlade and I aim to do everything you want me to do!");
    }

    public static void initRetrieveList(myList taskList) {
        if (taskList.getNumTasks() > 0) {
            System.out.println("It seems like you've had a task list from a previous session, here's how it looks"
                            + taskList.toString());
        }
    }

    public static void argumentError() {
        System.out.println("Too many arguments, please give me just 1 task to mark as completed");
    }

    public static void unknownCommand() {
        System.out.println("Unfortunately I don't know what you want me to do :L");
    }

    public static void todoError() {
        System.out.println("I don't understand your todo command, the syntax should be {todo} {description}");
    }

    public static void deadlineError() {
        System.out.println("I don't understand your deadline command, the syntax should be " +
                "{deadline} {description} /by {yyyy-MM-dd hhmm} with deadline datetime given in 24hr format");
    }

    public static void eventError() {
        System.out.println("I don't understand your deadline command, the syntax should be " +
                "{deadline} {description} /at {yyyy-MM-dd hhmm} /to {yyyy-MM-dd hhmm} " +
                "with deadline datetime given in 24hr format");
    }

    public static void printList(myList taskList) {
        System.out.println("Here's your list of tasks!" + taskList.toString());
    }

    public static void printFoundTasks(ArrayList<Task> taskArrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here's the tasks I found with your search term!\n");

        for (Task t : taskArrayList) {
            sb.append(t);
        }

        System.out.println(sb.toString());
    }

    public static void shutdown() {
        System.out.println("See you soon!");
    }
}
