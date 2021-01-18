import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> list;

    /**
     * An application that serves as a to-do list.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        System.out.println("POWERED BY JARVIS\n");
        greet();
        String userInput = "";
        String[] parseInput;
        String command;
        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            parseInput = userInput.split(" ");
            if (parseInput.length == 0) {
                // handle blank spaces input
                continue;
            }
            command = parseInput[0];
            switch (command) {
                case "list":
                    print(list);
                    break;
                case "todo":
                    handleToDo(userInput);
                    break;
                case "deadline":
                    handleDeadline(userInput);
                    break;
                case "event":
                    handleEvent(userInput);
                    break;
                case "done":
                    handleDone(parseInput);
                    break;
                case "bye":
                    break;
                default:
                    handleInvalidCommand();
            }
        }
        exit();
    }

    /**
     * Greet the user when he/she starts the application.
     */

    private static void greet() {
        print("Hello! I'm Jarvis.\n\t  How may I help you?");
    }

    /**
     * Say goodbye to the user when he/she exits the application.
     */

    private static void exit() {
        print("Goodbye. See you later!");
    }

    /**
     * Handle user input when an invalid command is given.
     */

    private static void handleInvalidCommand() {
        try {
            throw new DukeInvalidCommandException("Invalid Command!");
        } catch (DukeInvalidCommandException e) {
            print(e.getMessage());
        }
    }

    /**
     * Handle user input when user enters a "todo" command.
     * @param userInput User's input.
     */

    private static void handleToDo(String userInput) {
        try {
            if (userInput.split(" ").length == 1) {
                throw new DukeDescriptionException("You have not entered a description!");
            }
            String description = userInput.substring(5);
            addTask(new ToDo(description));
        } catch (DukeDescriptionException e) {
            print(e.getMessage());
        }
    }

    /**
     * Handle user input when user enters a "deadline" command.
     * @param userInput User's input.
     */

    private static void handleDeadline(String userInput) {
        try {
            if (userInput.split(" ").length == 1) {
                throw new DukeDescriptionException("You have not entered a description!");
            }
            if (userInput.split(" ").length <= 3) {
                throw new DukeDeadlineException("You have not entered a deadline for this task!");
            }
            String[] parseDeadline = userInput.substring(9).split(" /by ");
            addTask(new Deadline(parseDeadline[0], parseDeadline[1]));
        } catch (DukeDescriptionException e) {
            print(e.getMessage());
        } catch (DukeDeadlineException e) {
            print(e.getMessage());
        }
    }

    /**
     * Handle user input when user enters a "event" command.
     * @param userInput User's input.
     */

    private static void handleEvent(String userInput) {
        try {
            if (userInput.split(" ").length == 1) {
                throw new DukeDescriptionException("You have not entered a description!");
            }
            if (userInput.split(" ").length <= 3) {
                throw new DukeEventException("You have not entered the date/time for this event!");
            }
            String[] parseEvent = userInput.substring(6).split(" /at ");
            addTask(new Event(parseEvent[0], parseEvent[1]));
        } catch (DukeDescriptionException e) {
            print(e.getMessage());
        } catch (DukeEventException e) {
            print(e.getMessage());
        }
    }

    /**
     * Handle user input when user enters a "done" command.
     * @param parseInput Parsed user's input.
     */

    private static void handleDone(String[] parseInput) {
        try {
            int taskIndex = Integer.parseInt(parseInput[1])-1;
            markTaskAsDone(list.get(taskIndex));
        } catch (NumberFormatException e) {
            print("Please enter a numerical value as the list index!");
        } catch (IndexOutOfBoundsException e) {
            if (parseInput.length == 1) {
                print("You have not entered a list index!");
            } else {
                print("Please enter a valid list index!");
            }
        }
    }

    /**
     * Add task to the list.
     * @param task Task entered by the user.
     */

    private static void addTask(Task task) {
        list.add(task);
        print("Got it. I've added this task:\n\t\t" + task +
                "\n\n\t  You have " +
                list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list");
    }

    /**
     * Mark task as completed.
     * @param task Task entered by the user.
     */

    private static void markTaskAsDone(Task task) {
        task.markDone();
        print("Nice! I have marked this task as done:\n\t\t " + task);
    }

    /**
     * Print message to user.
     * @param message Welcome/Goodbye message or a description of the task added.
     */

    public static void print(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\t  " + message);
        System.out.println("\n\t____________________________________________________________\n");
    }

    /**
     * Print all tasks.
     * @param list A list of tasks entered by the user.
     */

    public static void print(List<Task> list) {
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\t  Your tasks:");
        int listCounter = 1;
        for (Task task : list) {
            System.out.println("  \t  " + listCounter + "." + task);
            listCounter++;
        }
        System.out.println("\n\t____________________________________________________________\n");
    }
}
