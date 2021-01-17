import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList;
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void greetUser() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void addCommand(String command) {
        Task newTask = new Task(command);
        System.out.printf("added: %s%n", command);
        taskList.add(newTask);
    }

    private static void printCommand() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
        }
    }

    private static void echoCommand(String command) {
        printHorizontalLine();
        if (command.equals("list")) {
            printCommand();
        } else if (command.contains("done")) {
            doneCommand(command);
        } else {
            addCommand(command);
        }
        printHorizontalLine();
    }

    private static void doneCommand(String command) {
        System.out.println("Nice! I've marked this task as done: ");
        String[] inputs = command.split(" ");
        int itemPosition = Integer.parseInt(inputs[1]) - 1;
        Task selectedTask = taskList.get(itemPosition);
        selectedTask.setDone();
        System.out.println("   " + selectedTask.toString());
    }

    private static void exitCommand() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        greetUser();
        taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            echoCommand(command);
        }
        exitCommand();
        sc.close();
    }
}