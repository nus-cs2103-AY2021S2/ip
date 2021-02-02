package duke.component;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    public static final String LINE = "____________________________________________________________";

    /**
     * Reads user's input command.
     * @return
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints bye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints done message.
     * @param t
     */
    public void showDone(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Prints delete message.
     * @param t
     * @param tl
     */
    public void showDelete(Task t, TaskList tl) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + tl.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints add message.
     * @param t
     * @param tl
     */
    public void showAdd(Task t, TaskList tl) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + tl.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints not found message.
     */
    public void showNotFound() {
        System.out.println("Task not found!");
    }

    /**
     * Prints found tasks.
     * @param tasks
     */
    public void showFound(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    /**
     * Prints all the task in the taskList.
     * @param tl
     */
    public void showList(TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> tasks = tl.getTasks();
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints error message.
     * @param msg
     */
    public void showError(String msg) {
        System.out.println(msg);
    }
}
