package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String command = null;
        if (sc.hasNextLine()) {
            command = sc.nextLine();
        }
        return command;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This method greets the user upon execution.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showExit() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        showLine();
        System.out.println("No existing task list was found. A new task list will be created.");
        showLine();
    }

    public void printList(TaskList taskList) {
        System.out.printf("Here are the %s in your list:%n", taskList.size() >= 2 ? "tasks" : "task");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
        }
    }

    public void showAddMessage(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        formattedPrint(task.toString());
        showSummaryMessage(listSize);
    }

    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        formattedPrint(task.toString());
    }

    public void showDeleteMessage(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        formattedPrint(task.toString());
        showSummaryMessage(listSize);
    }

    private void showSummaryMessage(int listSize) {
        System.out.printf("Now you have %d %s in the list.%n", listSize, listSize >= 2 ? "tasks" : "task");
    }

    private void formattedPrint(String message) {
        final String PRINT_FORMAT = "\t%s%n";
        System.out.printf(PRINT_FORMAT, message);
    }

    public void showError(String message) {
        System.out.printf("☹️ OOPS!!! %s%n", message);
    }

}
