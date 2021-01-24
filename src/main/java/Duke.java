import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static DateTimeFormatter formatter;
    private static List<Task> list;
    private static TaskStorage storage;
    private static Ui ui;

    /**
     * An application that serves as a to-do list.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        ui = new Ui();
        storage = new TaskStorage();
        list = storage.retrieveData();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        System.out.println("POWERED BY JARVIS\n");
        greet();
        String userInput = "";
        String[] parseInput;
        String command;
        while (!userInput.equals("bye")) {
            userInput = ui.readInput();
            if (userInput.isBlank()) {
                continue;
            }
            parseInput = userInput.split("\\s+");
            command = parseInput[0];
            switch (command) {
                case "list":
                    ui.print(list);
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
            storage.storeData(list);
        }
        exit();
    }

    /**
     * Greet the user when he/she starts the application.
     */

    private static void greet() {
        ui.print("Hello! I'm Jarvis.\n\t  How may I help you?");
    }

    /**
     * Say goodbye to the user when he/she exits the application.
     */

    private static void exit() {
        ui.print("Goodbye. See you later!");
    }

    /**
     * Handle user input when an invalid command is given.
     */

    private static void handleInvalidCommand() {
        try {
            throw new DukeInvalidCommandException("Invalid Command!");
        } catch (DukeInvalidCommandException e) {
            ui.print(e.getMessage());
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
            ui.print(e.getMessage());
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
            LocalDateTime dateTime = LocalDateTime.parse(deadlineDetails[1], formatter);
            addTask(new Deadline(deadlineDetails[0], dateTime));
        } catch (DukeDescriptionException e) {
            ui.print(e.getMessage());
        } catch (DukeDeadlineException e) {
            ui.print(e.getMessage());
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
            LocalDateTime dateTime = LocalDateTime.parse(eventDetails[1], formatter);
            addTask(new Event(eventDetails[0], dateTime));
        } catch (DukeDescriptionException e) {
            ui.print(e.getMessage());
        } catch (DukeEventException e) {
            ui.print(e.getMessage());
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
            ui.print("Please enter a numerical value as the list index!");
        } catch (IndexOutOfBoundsException e) {
            if (parseInput.length == 1) {
                ui.print("You have not entered a list index!");
            } else {
                ui.print("Please enter a valid list index!");
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
            ui.print("Please enter a numerical value as the list index!");
        } catch (IndexOutOfBoundsException e) {
            if (parseInput.length == 1) {
                ui.print("You have not entered a list index!");
            } else {
                ui.print("Please enter a valid list index!");
            }
        }
    }

    /**
     * Add task to the list.
     * @param task Task entered by the user.
     */

    private static void addTask(Task task) {
        list.add(task);
        ui.print("Got it. I've added this task:\n\t\t" + task +
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
        ui.print("I've removed this task:\n\t\t" + toRemove +
                "\n\n\t  You have " +
                list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list");
    }

    /**
     * Mark task as completed.
     * @param task Task entered by the user.
     */

    private static void markTaskAsDone(Task task) {
        task.markDone();
        ui.print("Nice! I have marked this task as done:\n\t\t " + task);
    }
}
