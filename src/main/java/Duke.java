import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String BOT_NAME = "Apollo the Robot";
    public static String INDENTATION = "    ";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void printlnWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
    }

    public static void printHorizontalLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
    }

    public static void addTask(Task task, ArrayList<Task> taskList) {
        taskList.add(task);
        printHorizontalLine();
        printlnWithIndentation("added: " + task.toString());
        printHorizontalLine();
    }

    public static void listTasks(ArrayList<Task> taskList) {
        printHorizontalLine();

        for(int i = 0; i < taskList.size(); i++ ) {
            int index = i + 1;
            printlnWithIndentation(index + ". " + taskList.get(i).toString());
        }

        printHorizontalLine();
    }

    public static void main(String[] args) {
        printHorizontalLine();
        printlnWithIndentation("Hello! I'm " + BOT_NAME + "!");
        printlnWithIndentation("What would you like to do today?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            String command = input.split(" ", 1)[0];

            if (command.equals("bye")) {
                printHorizontalLine();
                printlnWithIndentation("Bye. Hope to see you again soon!");
                printHorizontalLine();
                System.exit(0);
            } else if (command.equals("list")) {
                listTasks(taskList);
            }
            else {
                Task task = new Task(input);
                addTask(task, taskList);
            }
        }
    }
}
