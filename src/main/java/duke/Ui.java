package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    protected static String lineSpacing = "____________________________________________________________";

    protected static String taskConfirmation = "Got it. I've added this task:\n";

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + lineSpacing);
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printTaskAddedConfirmation(Task t, TaskList taskList) {
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

    public void printTaskDoneConfirmation(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task
                + "\n" + lineSpacing);
    }

    public void printTaskDeleteConfirmation(TaskList taskList, Task task) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("Noted! I've removed this task:\n" + task
                + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + lineSpacing);
    }

    public void printDukeExceptionMessage(DukeException e) {
        System.out.println("Duke has encountered an error: " + e.getMessage()
                + "\n" + lineSpacing);
    }
}
