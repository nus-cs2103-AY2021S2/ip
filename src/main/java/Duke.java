import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static String BOT_NAME = "Apollo the Robot";
    public static String INDENTATION = "    ";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public enum Command {
        LIST,
        DONE,
        BYE,
        TODO,
        DEADLINE,
        EVENT
    }

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

    public static void addTask(Command command, String input, ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();

        switch (command) {
            case TODO:
                Task todo = new Todo(input);
                taskList.add(todo);
                numberOfTasks += 1;
                printBetweenLines("Got it. I've added this task:",
                        INDENTATION + todo.toString(),
                        "Now you have " + numberOfTasks + " tasks in the list."
                );
                break;
            case DEADLINE:
                int indexOfBy = input.indexOf("/by");
                String deadlineMessage = input.substring(0, indexOfBy);
                String by = input.substring(indexOfBy + 4);
                Task deadline = new Deadline(deadlineMessage, by);
                taskList.add(deadline);
                numberOfTasks += 1;
                printBetweenLines("Got it. I've added this task:",
                        INDENTATION + deadline.toString(),
                        "Now you have " + numberOfTasks + " tasks in the list."
                );
                break;
            case EVENT:
                int indexOfAt = input.indexOf("/at");
                String eventMessage = input.substring(0, indexOfAt);
                String at = input.substring(indexOfAt + 4);
                Task event = new Event(eventMessage, at);
                taskList.add(event);
                numberOfTasks += 1;
                printBetweenLines("Got it. I've added this task:",
                        INDENTATION + event.toString(),
                        "Now you have " + numberOfTasks + " tasks in the list."
                );
                break;
        }
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
            String[] inputArr = input.split(" ", 2);

            Command command = Command.valueOf(inputArr[0].toUpperCase(Locale.ROOT));

            switch (command) {
                case LIST:
                    listTasks(taskList);
                    break;
                case DONE:
                    int index = Integer.parseInt(inputArr[1]) - 1;
                    Task task = taskList.get(index);
                    task.markAsDone();
                    printBetweenLines("Nice! I've marked this task as done:", task.toString());
                    break;
                case BYE:
                    addTask(command, inputArr[1], taskList);
                    break;
                case TODO:
                    addTask(command, inputArr[1], taskList);
                    break;
                case DEADLINE:
                    addTask(command, inputArr[1], taskList);
                    break;
                case EVENT:
                    addTask(command, inputArr[1], taskList);
                    break;
                default:
                    printBetweenLines("Invalid command, please try again!");
            }
        }
    }
}
