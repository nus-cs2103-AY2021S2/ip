import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    //user commands
    private final static String listCommand = "list";
    private final static String doneCommand = "done";
    private final static String exitCommand = "bye";
    private final static String ToDos = "todo"; //tasks without any date/time attached to it
    private final static String Deadlines = "deadline"; //tasks that need to be done before a specific date/time
    private final static String Events = "event"; //tasks that start at a specific time and ends at a specific time


    public static void getTasks() {
        int i = 1;
        System.out.println("____________________________________________________________\n"
        + (numberOfTask() <= 1 ? "Here is the task in your list: \n" : "Here are the tasks in your list: \n"));

        for (Task t: taskList) {
            System.out.println(i + ". "
                    + t.getType()
                    + t.getStatusIcon() + " "
                    + t.getDescription());
            i++;
        }

        System.out.println("____________________________________________________________\n");

    }

    public static Integer numberOfTask() {
        return taskList.size();
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

        } else if (description.contains(Deadlines)) {
            addDeadlines(description);
            storeTask();

        }else if (description.contains(Events)) {
            addEvents(description);
            storeTask();

        } else {
            addTodos(description);
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

    public static void Remark() {
        if (numberOfTask() <= 1) {
            System.out.println("Now you have " + numberOfTask() + " task in the list."
                    + "\n____________________________________________________________\n");
        } else {
            System.out.println("Now you have " + numberOfTask() + " tasks in the list."
                    + "\n____________________________________________________________\n");
        }
    }

    public static void addTodos(String todo) {
        String taskContent = todo.substring(4);
        Todo myTask = new Todo(taskContent);
        taskList.add(myTask);
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + myTask.type + "[ ] " + myTask.getDescription());
        Remark();
    }

    public static void addDeadlines(String deadline) {
        String[] taskSegments = deadline.split("/");
        String taskContent = taskSegments[0].substring(8);
        String taskTime = taskSegments[taskSegments.length - 1];
        Deadline myTask = new Deadline(taskContent, taskTime);
        taskList.add(myTask);

        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + myTask.type + "[ ] " + myTask.getDescription()
                + " (by: " + myTask.getTime() + ")");
        Remark();

    }

    public static void addEvents(String event) {
        String[] taskSegments = event.split("/");
        String taskContent = taskSegments[0].substring(5);
        String taskTime = taskSegments[taskSegments.length - 1];
        Event myTask = new Event(taskContent, taskTime);
        taskList.add(myTask);

        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + myTask.type + "[ ] " + myTask.getDescription()
                + " (at: " + myTask.getTime() + ")");
        Remark();
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
