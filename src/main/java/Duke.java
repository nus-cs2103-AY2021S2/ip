import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskList;
    private final static String PRINT_FORMAT = "\t%s%n";
    
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void greetUser() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void echoCommand(String inputLine) throws DukeException {
        String[] inputs = inputLine.split(" ");
        String command = inputs[0].toUpperCase();
        if (!DukeCommand.isContains(command)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            DukeCommand inputDukeCommand = DukeCommand.valueOf(command);
            String[] actions = Arrays.copyOfRange(inputs, 1, inputs.length);
            String actionString = String.join(" ", actions);
            inputDukeCommand.runCommand(actionString);
        }
    }

    public static void addCommand(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.printf(PRINT_FORMAT, newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", Duke.taskList.size());
    }

    public static void doneCommand(int index) {
        System.out.println("Nice! I've marked this task as done:");
        Task selectedTask = taskList.get(index);
        selectedTask.setDone();
        System.out.printf(PRINT_FORMAT, selectedTask.toString());
    }

    public static void listCommand() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
        }
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
            printHorizontalLine();
            try {
                echoCommand(command);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            printHorizontalLine();
        }
        exitCommand();
        sc.close();
    }
}