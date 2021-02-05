package UserInterface;

import Tasks.Task;
import Tasks.TaskList;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void handleList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no tasks in your list yet :)");
        } else {
            System.out.println("Here are the task(s) in your list:");
            tasks.printTasks();
        }
    }

    public void handleDone(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask.getStatusString());
    }

    public void handleDelete(Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.getStatusString());
    }

    public void handleAddTask(TaskList tasks, Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getStatusString());
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
