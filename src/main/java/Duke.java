import Task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK = "------------------------------------------------------------";
    private static final String INDENT = "    ";
    private static final String BOT_NAME = "Chip the Squirrel";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    enum TaskType {
        DEADLINE,
        TODO,
        EVENT
    }

    public static void printWithIndentation(String ... strings) {
        System.out.println(INDENT + LINE_BREAK);

        for (String s : strings) {
            System.out.println(INDENT + s);
        }

        System.out.println(INDENT + LINE_BREAK);
    }

    public static void printTasks() {
        if (tasks.size() == 0) {
            printWithIndentation("You have not added any tasks.");
        } else {
            String[] tasksArr = new String[tasks.size()];

            for (int i = 0; i < tasks.size(); i++) {
                tasksArr[i] = (i + 1) + "." + tasks.get(i).toString();
            }

            printWithIndentation(tasksArr);
        }
    }

    public static void addTask(TaskType taskType, String input) {
        String[] tokens;
        Task task;

        switch (taskType) {
            case TODO:
                task = new Todo(input);
                break;
            case DEADLINE:
                tokens = input.split(" /by ", 2);
                input = tokens[0];
                task = new Deadline(input, tokens[1]);
                break;
            case EVENT:
                tokens = input.split(" /at ", 2);
                input = tokens[0];
                task = new Event(input, tokens[1]);
                break;
            default:
                printWithIndentation("I do not understand.");
                return;
        }

        tasks.add(task);
        int numTasks =  tasks.size();
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";
        printWithIndentation("Got it! I've added this task:",
                "  " + task.toString(),
                "Now you have " + formattedTasksCount + " in the list.");
    }

    public static void main(String[] args) {
        printWithIndentation("Hello! I'm " + BOT_NAME + "!", "What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];

            switch(command) {
                case "bye":
                    printWithIndentation("Bye! Hope to see you again soon!");
                    System.exit(0);
                    break;
                case "todo":
                    addTask(TaskType.TODO, tokens[1]);
                    break;
                case "event":
                    addTask(TaskType.EVENT, tokens[1]);
                    break;
                case "deadline":
                    addTask(TaskType.DEADLINE, tokens[1]);
                    break;
                case "done":
                    int idx = Integer.parseInt(tokens[1]) - 1;
                    Task task = tasks.get(idx);
                    task.markAsDone();
                    printWithIndentation("Good Job! I've marked this task as done!", task.toString());
                    break;
                case "list":
                    printTasks();
                    break;
                default:
                    printWithIndentation("I do not understand.");
            }
        }
    }
}
