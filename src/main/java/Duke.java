import exceptions.DukeException;
import exceptions.InvalidOptionException;
import exceptions.UnrecognisedCommandException;
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

    public static void addTask(Commands command, String input, ArrayList<Task> taskList) throws InvalidOptionException {
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
                int indexOfBy = input.trim().indexOf("/by");

                if (indexOfBy == 0) {
                    throw new InvalidOptionException("DEADLINE");
                }

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
                int indexOfAt = input.trim().indexOf("/at");

                if (indexOfAt == 0) {
                    throw new InvalidOptionException("EVENT");
                }

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

        if (taskList.isEmpty()) {
            printlnWithIndentation("You have not added any tasks yet.");
        }

        for(int i = 0; i < taskList.size(); i++ ) {
            int index = i + 1;
            printlnWithIndentation(index + ". " + taskList.get(i).toString());
        }

        printHorizontalLine();
    }

    public static void doneTask(String input) {
        int index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        printBetweenLines("Nice! I've marked this task as done:", task.toString());
    }

    public static void deleteTask(String input) {
        int index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        Integer numberOfTasks = taskList.size();

        printBetweenLines("Noted. I've removed this task:",
                INDENTATION + task.toString(),
                "Now you have " + numberOfTasks.toString() + " tasks in the list.");
    }

    public static void handleInput(String s) throws DukeException {
        String input = s.trim();
        Commands command;

        if(input.equals("")) {
            return;
        }

        String[] inputArr = input.split(" ", 2);

        try {
            command = Commands.valueOf(inputArr[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new UnrecognisedCommandException(inputArr[0].toUpperCase(Locale.ROOT));
        }

        switch (command) {
            case LIST:
                listTasks(taskList);
                break;
            case DONE:
                try {
                    doneTask(inputArr[1]);
                    break;
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new InvalidOptionException(command.name());
                }
            case DELETE:
                try {
                    deleteTask(inputArr[1]);
                    break;
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new InvalidOptionException(command.name());
                }
            case BYE:
                printlnWithIndentation("Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            case TODO:
                try {
                    addTask(command, inputArr[1], taskList);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidOptionException(command.name());
                }
                break;
            case DEADLINE:
                try {
                    addTask(command, inputArr[1], taskList);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidOptionException(command.name());
                }
                break;
            case EVENT:
                try {
                    addTask(command, inputArr[1], taskList);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidOptionException(command.name());
                }
                break;
            default:
                printBetweenLines("Invalid command, please try again!");
        }
    }

    public static void main(String[] args) {
        printBetweenLines("Hello! I'm " + BOT_NAME + "!", "What would you like to do today?");

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String input = scanner.nextLine();

            try {
                handleInput(input);
            } catch(Exception e) {
                printBetweenLines("An error occurred:", e.getMessage());
            }
        }
    }
}
