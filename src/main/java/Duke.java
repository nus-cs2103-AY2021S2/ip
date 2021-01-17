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

    private static void printCommand() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
        }
    }

    private static void doneCommand(String command) {
        System.out.println("Nice! I've marked this task as done: ");
        String[] inputs = command.split(" ");
        int itemPosition = Integer.parseInt(inputs[1]) - 1;
        Task selectedTask = taskList.get(itemPosition);
        selectedTask.setDone();
        System.out.println("   " + selectedTask.toString());
    }

    private static void todoCommand(String command) {
        System.out.println("Got it. I've added this task: ");
        ToDo newToDo = new ToDo(command.substring(5));
        taskList.add(newToDo);

        System.out.println("   " + newToDo.toString());
        printSummary();
    }

    private static void deadlineCommand(String command) {
        System.out.println("Got it. I've added this task: ");
        int slashPosition = command.indexOf("/");
        Deadline newDeadline = new Deadline(command.substring(9, slashPosition - 1), command.substring(slashPosition + 4));
        taskList.add(newDeadline);

        System.out.println("   " + newDeadline.toString());
        printSummary();
    }

    private static void eventCommand(String command) {
        System.out.println("Got it. I've added this task: ");

        int slashPosition = command.indexOf("/");
        Event newEvent = new Event(command.substring(6, slashPosition - 1), command.substring(slashPosition + 4));
        taskList.add(newEvent);

        System.out.println("   " + newEvent.toString());
        printSummary();
    }

    private static void printSummary() {
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }

    private static void echoCommand(String command) {
        printHorizontalLine();
        if (command.equals("list")) {
            printCommand();
        } else if (command.contains("done")) {
            doneCommand(command);
        } else if (command.contains("todo")) {
            todoCommand(command);
        } else if (command.contains("deadline")) {
            deadlineCommand(command);
        } else if (command.contains("event")) {
            eventCommand(command);
        }
        printHorizontalLine();
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