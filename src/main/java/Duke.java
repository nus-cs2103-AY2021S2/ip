import main.java.Task;
import main.java.Todo;
import main.java.Deadline;
import main.java.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "        ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SEPARATOR = INDENTATION + HORIZONTAL_LINE;

    public static void main(String[] args) {
        displayWelcomeMsg();

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            switch (cmd) {
            case "list":
                printTasks(tasks);
                break;
            default:
                String[] arr = cmd.split(" ", 2);
                switch (arr[0]) {
                case "done":
                    int index = Integer.parseInt(arr[1]) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    displayMarkTaskAsDoneMsg(task);
                    break;
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    addTask(tasks, cmd);
                    displayAddTaskMsg(tasks);
                    break;
                default:
                    System.out.println(SEPARATOR);
                    System.out.println("Invalid command!");
                    System.out.println(SEPARATOR);
                    break;
                }
                break;
            }
            cmd = sc.nextLine();
        }
        displayExitMsg();
        sc.close();
    }

    public static void displayWelcomeMsg() {
        String logo = "         ____        _        \n"
            + "        |  _ \\ _   _| | _____ \n"
            + "        | | | | | | | |/ / _ \\\n"
            + "        | |_| | |_| |   <  __/\n"
            + "        |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(SEPARATOR);
        System.out.println(logo);
        System.out.println(INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?");
        System.out.println(SEPARATOR);
    }

    public static void displayExitMsg() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    public static void addTask(ArrayList<Task> tasks, String cmd) {
        String[] arr = cmd.split(" ", 2);
        String type = arr[0];
        String rest = arr[1];
        Task task;
        if (type.equals("todo")) {
            task = new Todo(rest);
        } else if (type.equals("deadline")) {
            String[] temp = rest.split(" /by ");
            String description = temp[0];
            String by = temp[1];
            task = new Deadline(description, by);
        } else {
            String[] temp = rest.split(" /at ");
            String description = temp[0];
            String at = temp[1];
            task = new Event(description, at);
        }
        tasks.add(task);
    }

    public static void displayAddTaskMsg(ArrayList<Task> tasks) {
        int size = tasks.size();
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "    " + tasks.get(size - 1));
        System.out.println(INDENTATION + "Now you have " + size + " task" + (size > 1 ? "s" : "") + " in the list.");
        System.out.println(SEPARATOR);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(INDENTATION + "    " + i + ". " + tasks.get(i - 1));
        }
        System.out.println(SEPARATOR);
    }

    public static void displayMarkTaskAsDoneMsg(Task task) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"+ INDENTATION + "    " + task);
        System.out.println(SEPARATOR);
    }
}
