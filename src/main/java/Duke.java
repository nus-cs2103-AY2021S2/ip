import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String LINE_BREAK = "------------------------------------------------------------";
    private static String INDENT = "    ";
    private static String BOT_NAME = "Chip the Squirrel";
    private static ArrayList<Task> tasks = new ArrayList<>();

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

    public static void addTask(String task) {
        tasks.add(new Task(task));

        printWithIndentation("added: " + task);
    }

    public static void main(String[] args) {
        printWithIndentation("Hello! I'm " + BOT_NAME + "!", "What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];

            switch(command) {
                case "bye":
                    printWithIndentation("Bye! Hope to see you again soon!");
                    System.exit(0);
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
                    addTask(input);
            }
        }
    }
}
