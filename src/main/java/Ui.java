import java.util.ArrayList;

public class Ui {

    public static String lineSpacing = "____________________________________________________________";

    public static String taskConfirmation = "Got it. I've added this task:\n";

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + lineSpacing);
    }

    public static void taskAddConfirmation(Task t, ArrayList<Task> tasks) {
        System.out.println(taskConfirmation + t
                + "\nNow you have " + tasks.size()
                + (tasks.size() < 2 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    public static void listTasks(ArrayList<Task> tasks) {
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

    public static void taskDoneConfirmation(ArrayList<Task> tasks, Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task
                + "\n" + lineSpacing);
    }

    public static void taskDeleteConfirmation(ArrayList<Task> tasks, Task task) {
        System.out.println("Noted! I've removed this task:\n" + task
                + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + lineSpacing);
    }

    public static void dukeExceptionMessage(DukeException e) {
        System.out.println("Duke has encountered an error: " + e.getMessage()
                + "\n" + lineSpacing);
    }
}
