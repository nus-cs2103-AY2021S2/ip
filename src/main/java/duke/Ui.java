package duke;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
        System.out.println("");
    }

    public void showError(String error) {
        System.out.println(error);
        System.out.println("");
    }

    public void showAddedTask(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + numOfTasks + " tasks in the list.");
        System.out.println("");
    }

    public void showRemovedTask(Task task, int numOfTasks) {
        System.out.println("Noted. I've removed this task:\n" + task +
                "\nNow you have " + numOfTasks + " tasks in the list.");
        System.out.println("");
    }

    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println("");
    }

    public void showAllTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
            System.out.println("");
        }
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showDueTasks(ArrayList<Task> list, String date) {
        LocalDate currentDate = LocalDate.parse(date);
        System.out.println("Here are the tasks due on " + currentDate + ": " );
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("");
    }

    public void showMatchingTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("Sorry! There are no matching task.");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
        System.out.println("");
    }
}