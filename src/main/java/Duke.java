import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static boolean isRunning;
    public static List<Task> taskList;

    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    public static void printGreeting() {
        String greeting = "Hello! I'm Marvin and I will assist you with your task management.\n"
                + "What can I do for you today?\n";
        System.out.println(greeting);
    }

    public static void startDuke() {
        printDivider();
        printGreeting();
        printDivider();
        isRunning = true;
        taskList = new ArrayList<>();
    }

    public static void waitForInput(Scanner in) {
        while (isRunning) {
            String input = in.nextLine();
            handleInput(input);
        }
    }

    public static void handleInput(String input) {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        if (command.equals("todo")) {
            handleTodo(inputArr[1]);
        } else if (command.equals("deadline")) {
            handleDeadline(inputArr[1]);
        } else if (command.equals("event")) {
            handleEvent(inputArr[1]);
        } else if (command.equals("bye")) {
            handleBye();
        } else if (command.equals("list")) {
            handleList();
        } else if (command.equals("done")) {
            handleDone(inputArr[1]);
        }
    }

    public static void handleTodo(String input) {
        Todo todo = new Todo(input);
        taskList.add(new Todo(input));
        printAddedTask(todo);
    }

    public static void handleDeadline(String input) {
        String[] inputArr = input.split(" /by ", 2);
        Deadline deadline = new Deadline(inputArr[0], inputArr[1]);
        taskList.add(deadline);
        printAddedTask(deadline);
    }

    public static void handleEvent(String input) {
        String[] inputArr = input.split(" /at ", 2);
        Event event = new Event(inputArr[0], inputArr[1]);
        taskList.add(event);
        printAddedTask(event);
    }

    public static void printAddedTask(Task task) {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
        printDivider();
    }

    public static void handleBye() {
        isRunning = false;
    }

    public static void handleList() {
        printTaskList();
    }

    public static void printTaskList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        if (taskList.size() > 0) {
            for (Task task : taskList) {
                System.out.println(String.format("%d.%s", count++, task));
            }
        }
        printDivider();
    }

    public static void handleDone(String strIdx) {
        Integer idx = Integer.parseInt(strIdx) - 1;
        Task task = taskList.get(idx);
        task.setDone(true);
        printDoneTask(task);
    }

    public static void printDoneTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printDivider();
    }

    public static void handleCommand(String command) {
        if (command.equals("bye")) {
            isRunning = false;
        } else if (command.equals("list")) {
            IntStream.range(0, taskList.size())
                    .forEach(idx -> {
                        System.out.println(idx + 1 + "." + taskList.get(idx));
                    });
        } else if (command.equals("done")) {

        }
    }

    public static void endDuke() {
        printByeMessage();
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void runDuke(Scanner in) {
        startDuke();
        waitForInput(in);
        endDuke();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        runDuke(in);
        in.close();
    }
}
