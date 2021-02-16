import main.java.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String indentation = "        ";
    private static final String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        displayWelcomeMsg();

        Scanner sc = new Scanner(System.in);
        String cmd = "";
        ArrayList<Task> tasks = new ArrayList<>();
        while (!cmd.equals("bye")) {
            cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                displayExitMsg();
                break;
            } else if (cmd.equals("list")) {
                printTasks(tasks);
            } else if (cmd.substring(0, 5).equals("done ")) {
                String[] arr = cmd.split(" ");
                int index = Integer.parseInt(arr[1]) - 1;
                Task task = tasks.get(index);
                task.markAsDone();
                displayMarkTaskAsDoneMsg(task);
            } else {
                addTask(tasks, cmd);
                displayAddTaskMsg(cmd);
            }
        }
        sc.close();
    }

    public static void displayWelcomeMsg() {
        String logo = "         ____        _        \n"
            + "        |  _ \\ _   _| | _____ \n"
            + "        | | | | | | | |/ / _ \\\n"
            + "        | |_| | |_| |   <  __/\n"
            + "        |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indentation + horizontalLine);
        System.out.println(logo);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?");
        System.out.println(indentation + horizontalLine);
    }

    public static void displayExitMsg() {
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(indentation + horizontalLine);
    }

    public static void addTask(ArrayList<Task> tasks, String task) {
        tasks.add(new Task(task));
    }

    public static void displayAddTaskMsg(String task) {
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "added: " + task);
        System.out.println(indentation + horizontalLine);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(indentation + "    " + i + ". " + tasks.get(i - 1));
        }
        System.out.println(indentation + horizontalLine);
    }

    public static void displayMarkTaskAsDoneMsg(Task task) {
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Nice! I've marked this task as done:\n"+ indentation + "    " + task);
        System.out.println(indentation + horizontalLine);
    }
}
