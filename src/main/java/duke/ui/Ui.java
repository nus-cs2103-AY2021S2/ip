package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "____________________________________________________________________________";
    private Scanner sc;

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(SEPARATOR);
    }

    private static void showToUser(String... replyMessage) {
        System.out.println(SEPARATOR);
        for (String m : replyMessage) {
            System.out.println(m);
        }
        System.out.println(SEPARATOR);
    }

    public void showWelcome() {
        showToUser("Hello! I'm Duke", "What can I do for you?");
    }

    public void showGoodbye() {
        showToUser("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        showToUser(errorMessage);
    }

    public void showCommands() {
        showToUser("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "List of recognised user commands:\n"
                + "  1. todo - adds a todo (E.g. todo borrow book)\n"
                + "  2. deadline - adds a deadline (E.g. deadline return book /by 2021-02-04)\n"
                + "  3. event - adds an event (E.g. event project meeting /at 2021-03-05)\n"
                + "  4. delete - removes a task from the lists of task (E.g. delete 2)\n"
                + "  5. done - marks a task as done in the list of tasks (E.g. done 2)\n"
                + "  6. list - displays the list of tasks\n"
                + "  7. bye - terminates Duke ☹");
    }

    public void showAddTask(Task task, TaskList tasks) {
        int numOfTasks = tasks.getSize();
        showToUser("Got it. I've added this task:\n" + "  " + task + "\n" + "Now you have " +
                    numOfTasks + (numOfTasks <= 1 ? " task" : " tasks") + " in the list.");
    }

    public void showDeleteTask(Task task, TaskList tasks) {
        int numOfTasks = tasks.getSize();
        showToUser("Noted. I've removed this task:\n" + "  " + task + "\n" + "Now you have " + numOfTasks +
                    " tasks in the list.");
    }

    public void showCompleteTask(Task task, TaskList tasks) {
        showToUser("Nice! I've marked this task as done:\n" + "  " + task);
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.getSize() <= 0) {
            showToUser("There are no tasks at the moment.");
        } else {
            int counter = 1;
            ArrayList<Task> taskList = tasks.getTaskList();
            showLine();
            System.out.println("Here are the tasks in your list:");

            for (Task task : taskList) {
                System.out.println("  " + counter + ". " + task);
                counter++;
            }
            showLine();
        }
    }
}
