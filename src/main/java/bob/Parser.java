package bob;

import bob.command.Command;
import bob.task.Deadline;
import bob.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Makes sense of the user command
 */
public class Parser {

    /**
     * Indicate which command the user is giving
     * @param userInput User's input
     * @return The command user is giving
     */
    public Command parseCommand(String[] userInput) {
        String command = userInput[0].toLowerCase();
        if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("list")) {
            return Command.LIST;
        } else if (command.equals("done")) {
            return Command.DONE;
        } else if (command.equals("todo")) {
            return Command.TODO;
        } else if (command.equals("event")) {
            return Command.EVENT;
        } else if (command.equals("deadline")) {
            return Command.DEADLINE;
        } else if (command.equals("delete")) {
            return Command.DELETE;
        } else if (command.equals("find")) {
            return Command.FIND;
        } else {
            return Command.INVALID;
        }
    }

    /**
     * Returns a valid integer given a user's input.
     * @param userInput User's full input
     * @param index The index at which the number is at
     * @return An integer that is the index of the tasks which the user is done with or wants to remove.
     * @throws DukeException if no integer was indicated or integer was invalid.
     */
    public int parseNumber(String userInput, int index) throws DukeException {
        int number;
        try {
            String indexString = userInput.substring(index).strip();
            number = Integer.parseInt(indexString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Remember to specify which task you are done with "
                    + "using a valid number", e);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid format, please try again using numbers only.", e);
        }
        return number;
    }

    /**
     * Returns a string representing the task name given the user's input.
     * @param userInput User's full input
     * @return A string representing the task name
     */
    public String parseName(String userInput) throws DukeException {
        try {
            int startIndex = userInput.indexOf(" ") + 1;
            int endIndex = userInput.indexOf("/");
            String name = "";
            if (startIndex == 0) {
                throw new DukeException("No name detected. Please try again.");
            }
            if (endIndex == -1) {
                name = userInput.substring(startIndex);
            } else {
                name = userInput.substring(startIndex, endIndex - 1);
            }
            return name;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No name detected. Please try again.", e);
        }
    }

    /**
     * Returns an event given the user's input
     * @param userInput User's full input
     * @param timeIndex The index at which /at: starts
     * @return An event that corresponds to the user input
     * @throws DukeException if user input do not include date or time, or
     * invalid format of date and time given
     */
    public Event parseEvent(String userInput, int timeIndex) throws DukeException {
        try {
            String name = userInput.substring(6, timeIndex - 1);
            String dateTime = userInput.substring(timeIndex + 5);
            String dateString = dateTime.substring(0, 10);
            String timeString = dateTime.substring(11);
            LocalDate date = LocalDate.parse(dateString);
            return new Event(name, date, timeString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of an event includes date and time.", e);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in the proper format: yyyy-mm-dd", e);
        }
    }

    /**
     * Returns a deadline task given the user's input.
     * @param userInput User's full input
     * @param timeIndex The index at which /by: starts
     * @return A deadline task that corresponds to the user input
     * @throws DukeException if user input do not include date or time, or
     * invalid format of date and time given
     */
    public Deadline parseDeadline(String userInput, int timeIndex) throws DukeException {
        try {
            String name = userInput.substring(9, timeIndex - 1);
            String dateTime = userInput.substring(timeIndex + 5);
            String dateString = dateTime.substring(0, 10);
            String timeString = dateTime.substring(11);
            LocalDate deadline = LocalDate.parse(dateString);
            return new Deadline(name, deadline, timeString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a deadline includes date and time.", e);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in the proper format: yyyy-mm-dd", e);
        }
    }
}
