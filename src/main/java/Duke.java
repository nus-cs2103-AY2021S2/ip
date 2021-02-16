import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        displayWelcomeMsg();

        Scanner sc = new Scanner(System.in);
        String cmd = "";
        ArrayList<String> tasks = new ArrayList<>();
        while (!cmd.equals("bye")) {
            cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                displayExitMsg();
                break;
            } else if (cmd.equals("list")) {
                printTasks(tasks);
            } else {
                tasks = addTask(tasks, cmd);
                String indentation = "        ";
                String horizontalLine = "____________________________________________________________";
                System.out.println(indentation + horizontalLine);
                System.out.println(indentation + "added: " + cmd);
                System.out.println(indentation + horizontalLine);
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
        String indentation = "        ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(indentation + horizontalLine);
        System.out.println(logo);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?");
        System.out.println(indentation + horizontalLine);
    }

    public static void displayExitMsg() {
        String indentation = "        ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(indentation + horizontalLine);
    }

    public static ArrayList<String> addTask(ArrayList<String> tasks, String task) {
        ArrayList<String> newTasks = new ArrayList<>(tasks);
        newTasks.add(task);
        return newTasks;
    }

    public static void printTasks(ArrayList<String> tasks) {
        String indentation = "        ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(indentation + horizontalLine);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(indentation + i + ". " + tasks.get(i - 1));
        }
        System.out.println(indentation + horizontalLine);
    }
}
