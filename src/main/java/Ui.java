import java.util.Scanner;

public class Ui {
    private static String INDENTATION = "    ";
    private static String HORIZON = "------------------------------------------------------";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println(INDENTATION + HORIZON);
        System.out.println(INDENTATION + "Hello! I'm Duke Y(^_^)Y");
        System.out.println(INDENTATION + "What can I do for you?\n");
        System.out.println(INDENTATION + HORIZON);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public void showCommandReply(String command, TaskList tasks) {
        System.out.println(INDENTATION + HORIZON);
        switch (command) {
        case "hello":
            System.out.println(INDENTATION + "Hello! I'm Duke Y(^_^)Y");
            System.out.println(INDENTATION + "What can I do for you?\n");
            break;
        case "bye":
            System.out.println(INDENTATION + "Bye. (>_<) Hope to see you again soon! ");
            break;
        case "list":
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSizeOfTasks(); i++) {
                System.out.println(INDENTATION + (i + 1) + "." + tasks.getTask(i).getTaskInfo());
            }
            break;

        default:
            System.out.println(INDENTATION + "Got it. I've added this task:");
            System.out.println(INDENTATION + tasks.getTask(tasks.getSizeOfTasks() - 1).getTaskInfo());
            System.out.println(INDENTATION + "Now you have " + tasks.getSizeOfTasks() + " tasks in the list.");
        }
        System.out.println(INDENTATION + HORIZON);
    }

    public void showErrorReply(String reply) {
        System.out.println(INDENTATION + HORIZON);
        switch (reply) {
        case "error_no_meaning":
            System.out.println(INDENTATION + "OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case "error_done_empty":
        case "error_delete_empty":
        case "error_date_empty":
            System.out.println(INDENTATION + "OOPS!!! The number cannot be empty :-(");
            break;
        case "error_done_no_meaning":
        case "error_delete_no_meaning":
        case "error_date_no_meaning":
            System.out.println(INDENTATION + "OOPS!!! Please input the number of the Task :-(");
            break;
        case "error_done_non_existed_task":
        case "error_delete_non_existed_task":
        case "error_date_non_existed_task":
            System.out.println(INDENTATION + "OOPS!!! the Task you choosing isn't existed :-(");
            break;
        case "error_todo_empty":
        case "error_deadline_empty":
        case "error_event_empty":
            System.out.println(INDENTATION + "OOPS!!! The description of a task cannot be empty. :-(");
            break;
        case "error_deadline_by":
            System.out.println(INDENTATION + "OOPS!!! The deadline of a deadline task should be meaningful. :-(");
            System.out.println(INDENTATION + "Please enter according to the format eg.description /by YYYY-MM-DD");
            break;
        case "error_event_at":
            System.out.println(INDENTATION + "OOPS!!! The period of a event task should be meaningful. :-(");
            System.out.println(INDENTATION + "Please enter according to the format eg.description /at YYYY-MM-DD");
            break;
        }
        System.out.println(INDENTATION + HORIZON);
    }

    public void showDoneReply(int number, TaskList tasks) {
        System.out.println(INDENTATION + HORIZON);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + (number + 1) + "." + tasks.getTask(number).getTaskInfo());
        System.out.println(INDENTATION + HORIZON);
    }

    public void showDeleteReply(int number, TaskList tasks) {
        System.out.println(INDENTATION + HORIZON);
        System.out.println(INDENTATION + "Noted. I've removed this task: ");
        System.out.println(INDENTATION + (number + 1) + "." + tasks.getTask(number).getTaskInfo());
        System.out.println(INDENTATION + "Now you have " + (tasks.getSizeOfTasks() - 1) + " tasks in the list.");
        System.out.println(INDENTATION + HORIZON);
    }

    public void showDateReply(int number, TaskList tasks) {
        System.out.println(INDENTATION + HORIZON);
        System.out.println(tasks.getTask(number).getPeriodDays());
        System.out.println(INDENTATION + HORIZON);
    }
}
