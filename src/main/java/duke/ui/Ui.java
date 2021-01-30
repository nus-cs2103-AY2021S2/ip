package duke.ui;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void displayAddedTask(Task task, int num) {
        System.out.println();
        System.out.println("☺ Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + num + " tasks in the list.");
        System.out.println();
    }

    public void displayDoneTask(Task task) {
        System.out.println();
        System.out.println("☺ Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    public void displayDeletedTask(Task task, int num) {
        System.out.println();
        System.out.println("☺ Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + num + " duke.tasks in the list.");
        System.out.println();
    }

    public void displayMatchingTasks(ArrayList<Task> tasks) {
        System.out.println();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            System.out.println(i + "." + t);
        }
        System.out.println();
    }

    public void displayList(ArrayList<Task> tasks) {
        System.out.println();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            System.out.println(i + "." + t);
        }
        System.out.println();
    }

    public void printExceptions(String message) {
        System.out.println();
        System.err.println(message);
        System.err.println();
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    public void goodbyeMessage() {
        System.out.println();
        System.out.println("Bye. Hope to see you again soon! ☺");
        sc.close();
    }
}
