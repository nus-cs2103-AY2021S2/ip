package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static String lineSpacing = "____________________________________________________________";

    public static String taskConfirmation = "Got it. I've added this task:\n";

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + lineSpacing);
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void taskAddConfirmation(Task t, TaskList taskList) {
        int numTasks = taskList.getTasks().size();
        System.out.println(taskConfirmation + t
                + "\nNow you have " + numTasks
                + (numTasks < 2 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    public void listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list. Hooray!\n" + lineSpacing);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task : tasks) {
            System.out.println(num + "." + task);
            num++;
        }
        System.out.println(lineSpacing);
    }

    public void taskDoneConfirmation(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task
                + "\n" + lineSpacing);
    }

    public void taskDeleteConfirmation(TaskList taskList, Task task) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("Noted! I've removed this task:\n" + task
                + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    public void printMatchingTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list. :(\n" + lineSpacing);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int num = 1;
            for (Task task : tasks) {
                System.out.println(num + "." + task);
                num++;
            }
            System.out.println(lineSpacing);
        }
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + lineSpacing);
    }

    public void dukeExceptionMessage(DukeException e) {
        System.out.println("Duke has encountered an error: " + e.getMessage()
                + "\n" + lineSpacing);
    }
}
