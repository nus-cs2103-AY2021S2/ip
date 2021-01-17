import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private final static String listCommand = "list";
    private final static String doneCommand = "done";
    private final static String exitCommand = "bye";

    public static void getTasks() {
        System.out.println("____________________________________________________________\n");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". "
                    + taskList.get(i).getStatusIcon() + " "
                    + taskList.get(i).getDescription());
        }

        System.out.println("____________________________________________________________\n");

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
        String description = sc.nextLine();

        if (description.equals(listCommand)) {
            getTasks();
            storeTask();

        } else if ((description.toLowerCase()).equals(exitCommand)) {
            Exit();

        } else if (description.contains("done")) {
            int taskIndex = Integer.parseInt(description.replaceAll("[^0-9]", ""));
            updateTaskStatus(taskIndex);
            storeTask();

        } else{
            Task myTask = new Task(description);
            taskList.add(myTask);
            System.out.println("____________________________________________________________\n"
                    + "added: " + myTask.getDescription()
                    + "\n____________________________________________________________\n");
            storeTask();
        }
    }

    public static void updateTaskStatus(Integer index) {
        taskList.get(index - 1).markAsDone();

        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + taskList.get(index - 1).getStatusIcon() + " "
                + taskList.get(index - 1).getDescription()
                + "\n____________________________________________________________\n");
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
