import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> taskList = new ArrayList<>();

    public static void getTasks() {
        System.out.println("____________________________________________________________\n");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }

        System.out.println("____________________________________________________________\n");

        storeTask();
    }

    public static void Greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke, 恭喜发财.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

    }

    public static void storeTask() {
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        String listCommand = "list";
        String exitCommand = "bye";

        if (task.equals(listCommand)) {
            getTasks();

        } else if ((task.toLowerCase()).equals(exitCommand)) {
            Exit();

        } else{
            taskList.add(task);
            System.out.println("____________________________________________________________\n"
                    + "added: " + task
                    + "\n____________________________________________________________\n");
            storeTask();
        }
    }

    public static void Exit() {
            String exit = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "Wish you all the best for CS2103T\n"
            + "____________________________________________________________\n";

            System.out.println(exit);

        }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Greet();

        storeTask();
    }
}
