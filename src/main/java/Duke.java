import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> ls = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partition = "\n/**********************************************************/\n";

        System.out.println(logo + "\nHello! I'm Duke.\n" + "What can I do for you?\n" + partition);
        command = sc.next();

        while(!command.equals("bye")) {
            switch (command) {
                case "list" :
                    printTasks();
                    System.out.println(partition);
                    break;
                case "done" :
                    int num = sc.nextInt();
                    markAsDone(num);
                    System.out.println(partition);
                    break;
                default :
                    addTask(new Task(command));
                    System.out.println(partition);
            }

            command = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
    }

    public static void addTask(Task task) {
        ls.add(task);
        System.out.println("added: " + task.getName());
    }

    public static void printTasks() {
        for (int i = 1; i <= ls.size(); i++) {
            System.out.println("" + i + ". " + ls.get(i - 1).getStatus());
        }
    }

    public static void markAsDone(int taskNum) {
        ls.get(taskNum - 1).markDone();
        System.out.println("This task is marked as done:\n" + ls.get(taskNum - 1).getStatus());
    }
}
