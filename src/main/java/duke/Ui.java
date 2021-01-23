package duke;

import duke.tasks.Task;
import java.util.Scanner;
import java.util.List;

public class Ui {

    public Ui() {}

    public String read() {
        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();

        return action;
    }

    public void printDivider() {
        String divider = "    ___________________________________________";
        System.out.println(divider);
    }

    public void welcome() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    public void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void print(String str) {
        System.out.println(str);
    }
    public void addPrint() {
        System.out.println("     Got it. I've added this task: ");
    }

    public void showLoadingError() {
        System.out.println("     Unable to load file. Creating new one");
    }

    public void countTasks(TaskList list) {
        System.out.println("     Now you have " + list.getList().size() + " tasks in the list.");
    }

    public static void printRemoved() {
        System.out.println("     Noted. I've removed this task: ");
    }

    public static void printMarked() {
        System.out.println("     " + "Nice! I've marked this task as done:");
    }

    public void printStored(TaskList list) {
        List<Task> taskList = list.getList();

        System.out.println("     Here are the tasks in your list:");

        for (int i = 0; i < list.getList().size(); i++) {
            Task task = taskList.get(i);
            System.out.println("      " + (i + 1) + "." + task.toString().trim());
        }
    }
}
