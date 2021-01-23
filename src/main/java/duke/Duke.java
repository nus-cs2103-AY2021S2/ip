package duke;

import duke.data.Data;

import static duke.data.Data.*;

import static duke.Display.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected static ArrayList<Task> tasks;
    private final static Scanner sc = new Scanner(System.in);

    private static void addTask(CommandType type, String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("I can't add an empty menu item!");
        }
        switch (type) {
        case TODO:
            addTodo(command[1]);
            break;
        case DEADLINE:
            addDeadline(command[1]);
            break;
        case EVENT:
            addEvent(command[1]);
            break;
        }
    }

    private static void addTodo(String desc) {
        Task task = new Todo(desc);
        tasks.add(task);
        displayAddedTask(task);
    }

    private static void addDeadline(String desc) throws DukeException {
        String[] args = desc.split(" /by ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your order isn't complete...");
        }
        try {
            Task task = new Deadline(args[0], convertStringToDate(args[1]));
            tasks.add(task);
            displayAddedTask(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static void addEvent(String desc) throws DukeException {
        String[] args = desc.split(" /at ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your order isn't complete...");
        }
        try {
            Task task = new Event(args[0], convertStringToDate(args[1]));
            tasks.add(task);
            displayAddedTask(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static void markDone(String[] command) throws DukeException {
        if (command.length > 2) {
            throw new DukeException("We can't serve more than 1 order at a time...\nTry again!");
        }
        try {
            Task toMarkDone = tasks.get(Integer.parseInt(command[1]) - 1);
            toMarkDone.markDone();
            displayDone(toMarkDone);
        } catch (Exception e) {
            throw new DukeException("Oops! That doesn't seem like a valid order number...\nTry again!");
        }
    }

    private static void deleteTask(String[] command) throws DukeException {
        try {
            Task task = tasks.remove(Integer.parseInt(command[1]) - 1);
            displayRemovedTask(task);
        } catch (Exception e) {
            throw new DukeException("Oops! That doesn't seem like a valid order number...\nTry again!");
        }
    }

    private static CommandType getCommandType(String command) throws DukeException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (Exception e) {
            throw new DukeException("That doesn't seem to be an item on our menu...\nTry again!");
        }
    }

    public static void handleInput() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            String[] command = input.split(" ", 2);
            try {
                CommandType type = getCommandType(command[0]);
                switch (type) {
                case LIST:
                    displayAllTasks();
                    break;
                case DONE:
                    markDone(command);
                    break;
                case TODO:
                    // Fallthrough
                case EVENT:
                    // Fallthrough
                case DEADLINE:
                    addTask(type, command);
                    break;
                case DELETE:
                    deleteTask(command);
                    break;
                default:
                    throw new DukeException("Hmm... That doesn't seem to be an item in our menu...\nTry again!");
                }

                updateDataFile(tasks);

            } catch (DukeException e) {
                displayError(e.getMessage());
            }
        }
    }

    public static LocalDateTime convertStringToDate(String date) throws DukeException {
        // assume that date is in dd/mm/yyyy
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            throw new DukeException("There was something wrong with the format of your date and/or time!\n" +
                    "Make sure it's in the format <dd/MM/yyyy HHmm>!");
        }
    }

    public static void main(String[] args) throws DukeException {
        tasks = initialiseList();
        displayWelcome();
        handleInput();
        displayFarewell();
        sc.close();
    }
}

