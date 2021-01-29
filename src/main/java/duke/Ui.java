package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    String readCommand() {
        return sc.nextLine();
    }

    void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void showLine() {
        System.out.println("_______________________________________________________");
    }

    void showError(String msg) {
        System.out.println(msg);
    }

    public void showExit() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    void showLoadingError() {
        System.out.println("Unable to load the file. Empty list created.");
    }

    void showAdd(String task, int size) {
        System.out.println("Got it. I've added this task:");
        showTaskAndSize(task, size);
    }

    public void showDelete(String task, int size) {
        System.out.println("Noted, I've removed this task: ");
        showTaskAndSize(task, size);
    }

    public void showDone(String task, int size) {
        System.out.println("Nice! I've mark this task as done");
        showTaskAndSize(task, size);
    }

    void showTaskAndSize(String task, int size) {
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void showList(List<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There is currently no task in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            int index = 1;
            for (Task t: list) {
                System.out.println(index + "." + t);
                index++;
            }
        }
    }
}
