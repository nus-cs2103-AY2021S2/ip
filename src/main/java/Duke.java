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
            if (userInput.isBlank()) {
                continue;
            }
            parseInput = userInput.split("\\s+");
            command = parseInput[0];
            switch (command) {
                case "list":
                    print(list);
                    break;
                case "todo":
                    handleToDo(userInput, parseInput);
                    break;
                case "deadline":
                    handleDeadline(userInput, parseInput);
                    break;
                case "event":
                    handleEvent(userInput, parseInput);
                    break;
                case "done":
                    handleDone(parseInput);
                    break;
                case "delete":
                    handleDelete(parseInput);
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
     * @param parseInput Parsed user's input.
     */

    private static void handleToDo(String userInput, String[] parseInput) {
        try {
            if (parseInput.length == 1) {
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
     * @param parseInput Parsed user's input.
     */

    private static void handleDeadline(String userInput, String[] parseInput) {
        try {
            if (parseInput.length == 1) {
                throw new DukeDescriptionException("You have not entered a description!");
            }
            String[] deadlineDetails = userInput.substring(9).split(" /by ");
            if (deadlineDetails.length < 2 || deadlineDetails[1].isBlank()) {
                throw new DukeDeadlineException("You have not entered a deadline for this task!");
            }
            addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
        } catch (DukeDescriptionException e) {
            print(e.getMessage());
        } catch (DukeDeadlineException e) {
            print(e.getMessage());
        }
    }

    /**
     * Handle user input when user enters an "event" command.
     * @param userInput User's input.
     * @param parseInput Parsed user's input.
     */

    private static void handleEvent(String userInput, String[] parseInput) {
        try {
            if (parseInput.length == 1) {
                throw new DukeDescriptionException("You have not entered a description!");
            }
            String[] eventDetails = userInput.substring(6).split(" /at ");
            if (eventDetails.length < 2 || eventDetails[1].isBlank()) {
                throw new DukeEventException("You have not entered the date/time for this event!");
            }
            addTask(new Event(eventDetails[0], eventDetails[1]));
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
            if (parseInput.length > 2) {
                throw new NumberFormatException();
            }
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
     * Handle user input when user enters a "delete" command.
     * @param parseInput Parsed user's input.
     */

    private static void handleDelete(String[] parseInput) {
        try {
            if (parseInput.length > 2) {
                throw new NumberFormatException();
            }
            int taskIndex = Integer.parseInt(parseInput[1])-1;
            deleteTask(taskIndex);
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
     * Remove task from the list.
     * @param taskIndex The index of the task to remove.
     */

    private static void deleteTask(int taskIndex) {
        Task toRemove = list.get(taskIndex);
        toRemove.markIncomplete();
        list.remove(taskIndex);
        print("I've removed this task:\n\t\t" + toRemove +
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

    private static void print(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\t  " + message);
        System.out.println("\n\t____________________________________________________________\n");
    }

    /**
     * Print all tasks.
     * @param list A list of tasks entered by the user.
     */

    private static void print(List<Task> list) {
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
