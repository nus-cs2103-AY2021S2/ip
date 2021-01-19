import tasks.Task;

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

    public static void printBetweenLines(String ... strings) {
        printHorizontalLine();

        for (int i = 0; i < strings.length; i++) {
            printlnWithIndentation(strings[i]);
        }

        printHorizontalLine();
    }

    public static void addTask(Task task, ArrayList<Task> taskList) {
        taskList.add(task);
        printBetweenLines("added: " + task.toString());
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
        printBetweenLines("Hello! I'm " + BOT_NAME + "!", "What would you like to do today?");

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] command = input.split(" ", 5);


            if (command[0].equals("bye")) {
                printBetweenLines("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (command[0].equals("list")) {
                listTasks(taskList);
            } else if (command[0].equals("done")) {
                int index = Integer.parseInt(command[1]) - 1;
                Task task = taskList.get(index);
                task.markAsDone();
                printBetweenLines("Nice! I've marked this task as done:", task.toString());
            }
            else {
                Task task = new Task(input);
                addTask(task, taskList);
            }
        }
    }
}
