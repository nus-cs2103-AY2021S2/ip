package duke;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ui {
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
        System.out.println("");
        return logo;
    }

    public String showError(String error) {
        System.out.println(error);
        System.out.println("");
        return error + "/n";
    }

    public String showAddedTask(Task task, int numOfTasks) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + numOfTasks + " tasks in the list. \n";
    }

    public String showRemovedTask(Task task, int numOfTasks) {
        return "Noted. I've removed this task:\n"
                + task + "\nNow you have " + numOfTasks + " tasks in the list. \n";
    }

    public String showDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    public String showAllTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "There are no tasks in your list. \n";
        } else {
            String result = "Here are the tasks in your list: \n";
            for (int i = 0; i < list.size(); i++) {
                result += String.valueOf(i + 1) + "." + list.get(i).toString() + "\n";
            }
            return result;
        }
    }

    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showDueTasks(ArrayList<Task> list, String date) {
        LocalDate currentDate = LocalDate.parse(date);
        String result = "Here are the tasks due on " + String.valueOf(currentDate) + ": " + "\n";
        for (int i = 0; i < list.size(); i++) {
            result += String.valueOf(i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return result;
    }

    public String showMatchingTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "Sorry! There are no matching task.";
        } else {
            String result = "Here are the matching tasks in your list: " + "\n";
            for (int i = 0; i < list.size(); i++) {
                result += String.valueOf(i + 1) + ". " + list.get(i).toString() + "\n";
            }
            return result;
        }
    }
}
