package ui;

import tasks.TaskList;
import tasks.Task;

import java.util.Scanner;

public class Ui {
    public Ui() {}

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showGreeting() {
        System.out.println("\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n");
    }

    public void showError(String message) {
        System.out.println("\t____________________________________________________________\n"
                + message + "\t____________________________________________________________\n");
    }

    public String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    public void showTasks(TaskList tasks) {
        System.out.println("\t____________________________________________________________\n"
                + "\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public void showEmptyList() {
        System.out.println("\t____________________________________________________________\n"
                + "\tThere are no items in your list.\n"
                + "\t____________________________________________________________\n");
    }

    public void showIOError() {
        System.out.println("\t____________________________________________________________\n"
                + "\tError happened while trying to edit save file.\n"
                + "\t____________________________________________________________\n");
    }

    public void showOutOfBoundsError() {
        System.out.println("\t____________________________________________________________\n"
                + "\tPlease enter the date (DD/MM/YYYY) with optional\n"
                + "\ttime (in 24 hours format) after \"/by\" for Deadline Tasks\n"
                + "\tor date with optional start and end time after \"/at\" \n"
                + "\tfor Event Tasks.\n"
                + "\t____________________________________________________________\n");
    }

    public void showDateTimeParseError() {
        System.out.println("\t____________________________________________________________\n"
                + "\tPlease enter in DD/MM/YYYY format (eg. 02/04/2000) for dates\n"
                + "\tand in 24 hour format (eg. 1830) for times.\n"
                + "\t____________________________________________________________\n");
    }

    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + task.toString() + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    public void showDeleteTask(TaskList tasks, Task task) {
        System.out.println("\t____________________________________________________________\n"
                + "\tNoted. I've removed this task:\n"
                + "\t   " + task.toString() + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    public void showDoneTask(TaskList tasks, int index) {
        System.out.println("\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n\t\t"
                + tasks.get(index - 1).toString() + "\n"
                + "\t____________________________________________________________\n");
    }

    public void showByeMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }
}
