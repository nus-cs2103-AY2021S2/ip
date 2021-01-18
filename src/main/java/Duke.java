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

        do {
            userInput = sc.nextLine();
            System.out.println("     _______________________________________");
            if (userInput.equals("list")) {
                System.out.println("     Here are the tasks in your list");
                list(userTasks);
            } else if (userInput.equals("blah")) {
                blah();
            } else if (userInput.equals("bye")) {
                bye();
            } else if (userInput.substring(0, 4).equals("done")) {
                int taskNum = Integer.parseInt(userInput.substring(5)) - 1;
                done(userTasks.get(taskNum));
            } else {
                userTasks.add(new Task(false, userInput));
                System.out.println("     added: " + userInput);
            }

            System.out.println("     _______________________________________\n");

        } while(!userInput.equals("bye"));

    }
}
