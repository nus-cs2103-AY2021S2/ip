import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void blah() {
        System.out.println("     blah");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void done(Task task) {
        task.completeTask();
        System.out.println("     Nice! I've marked this task as done:\n     " + task);
    }

    public static void addTask(String type, ArrayList<Task> tasks, Scanner sc) {
        String taskName = "";
        Task task = null;
        if (type.equals("todo")) {
            taskName = sc.nextLine();
            task = new ToDo(false, taskName);
        } else {
            String placeholder = "";
            String dateTime;
            while (!placeholder.equals("/by") && !placeholder.equals("/at")) {
                taskName += (placeholder + " ");
                placeholder = sc.next();
            }
            dateTime = sc.nextLine();
            if (type.equals("deadline")) {
                task = new Deadline(false, taskName, dateTime);
            } else if (type.equals("event")) {
                task = new Event(false, taskName, dateTime);
            }
        }
        tasks.add(task);
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        ArrayList<Task> userTasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Hello from\n" + logo);
        System.out.println("     What can I do for you?");
        System.out.println("     _______________________________________\n");

        while (sc.hasNext()) {
            userInput = sc.next();
            System.out.println("     _______________________________________");
            if (userInput.equals("list")) {
                System.out.println("     Here are the tasks in your list");
                list(userTasks);
            } else if (userInput.equals("blah")) {
                blah();
            } else if (userInput.equals("bye")) {
                bye();
                System.out.println("     _______________________________________\n");
                break;
            } else if (userInput.equals("done")) {
                done(userTasks.get(sc.nextInt() - 1));
            } else {
                System.out.println("     Got it. I've added this task:");
                addTask(userInput, userTasks, sc);
            }
            System.out.println("     _______________________________________\n");
        }
        sc.close();
    }
}
