package duke.ui;

import duke.duke.Duke;
import duke.tasks.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * Handles input/output of the application.
 * Acquires user input and prints messages to the console.
 */
public class Ui {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    /**
     * Prints welcome message.
     * @param bot a Duke object that manages task list operations
     */
    public static void showWelcomeMessage(Duke bot) {
        pw.printf("Hello from%n%s%n", bot.getLogo());
        pw.println("Hello! I'm Duke");
        pw.println("What can I do for you?");

        for (int i = 0; i < 50; i++) {
            pw.print('\u2500');
        }
        pw.println();
        pw.flush();
    }

    /**
     * Prints message that requests for user input.
     */
    public static void askForUserRequest() {
        pw.println("Anything else?");
        pw.flush();
    }

    /**
     * Prints list of task for the user.
     * @param list list of task to be printed
     */
    public static void showTasksToUser(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            pw.printf("%d.%s%n", i + 1, list.get(i));
        }
        pw.flush();
    }

    /**
     * Prints an empty line.
     */
    public static void printEmptyLine() {
        pw.println();
        pw.flush();
    }

    /**
     * Prints message when a new task is added.
     * @param task the new task added
     * @param list list of tasks
     */
    public static void showAddTaskMessage(Task task, List<Task> list) {
        pw.println("Got it. I've added this task:");
        pw.printf(" %s%n", task);
        pw.printf("Now you have %d tasks in the list.%n", list.size());
        pw.flush();
    }

    /**
     * Prints message when a task is removed.
     * @param task the task to be removed
     * @param list list of tasks
     */
    public static void showRemoveTaskMessage(Task task, List<Task> list) {
        pw.println("Noted. I've removed this task:");
        pw.printf(" %s%n", task);
        pw.printf("Now you have %d tasks in the list.%n", list.size());
        pw.flush();
    }

    /**
     * Prints message when task is marked as done.
     * @param list list of tasks
     * @param id index of task to be marked as done
     */
    public static void showDoneTaskMessage(List<Task> list, int id) {
        pw.println("Nice! I've marked this task as done:");
        pw.printf(" %s%n", list.get(id));
        pw.flush();
    }

    /**
     * Prints message upon exit of the application
     */
    public static void showExitMessage() {
        pw.println("Bye. Hope to see you again soon!");
        pw.flush();
        pw.close();
    }

    /**
     * Prints a message to the console.
     * @param message message to be shown to the user
     */
    public static void print(String message) {
        pw.println(message);
        pw.flush();
    }

    /**
     * Reads the user input.
     * @return user input as string
     * @throws IOException if an IO error occurs
     */
    public static String readUserInput() throws IOException {
        return br.readLine();
    }
}
