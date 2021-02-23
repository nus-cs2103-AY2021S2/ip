package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class reads lines and extracts
 * commands from read lines in the command-line.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Parser {

    /**
     * This method converts a LocalDate into String format.
     *
     * @param date This is the date in LocalDate format
     * @return Returns a String date
     */
    public String localDateToString(LocalDate date) throws DukeException {
        try {
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new DukeException("Invalid date provided!");
        }
    }

    /**
     * This method converts a String date into LocalDate format.
     *
     * @param dateString This is the date in String format
     * @return Returns a LocalDate
     */
    public LocalDate stringToLocalDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new DukeException("Invalid date provided!");
        }

    }

    /**
     * This method extracts the command from the input
     * line that was read.
     *
     * @param input This receives a String input
     * @return String This returns a command in string format
     */
    public String getCommand(String input) {
        String command = input.split(" ")[0];
        return command;
    }

    public String getArguments(String input) {
        // Skips command and gets everything after command
        String arguments = input.split(" ", 2)[1];
        return arguments;
    }

    /**
     * This method returns the integer index from input.
     *
     * @param input String with integer index
     * @return Integer index
     * @throws DukeException When input does not have integer index
     */
    public int getIndex(String input) throws DukeException {
        try {
            // Minus 1 to adjust for 0-based indexing in TaskList
            int i = Integer.parseInt(input.split(" ")[1]) - 1;
            return i;
        } catch (Exception e) {
            throw new DukeException("Invalid Index!");
        }
    }

    /**
     * This method gets the name of a To-Do task
     *
     * @param input This is the user input
     * @return String This is the To-Do task name
     * @throws DukeException On input error.
     */
    public String getTodoName (String input) throws DukeException {
        try {
            return input.split(" ", 2)[1].trim();
        } catch (Exception e) {
            throw new DukeException("Hmm... You are either lacking a \"name\" detail!");
        }
    }

    /**
     * This method gets the Event or Deadline name
     *
     * @param input This is the user input
     * @return String This is the Event or Deadline name
     * @throws DukeException On input error.
     */
    public String getEventOrDeadlineName(String input) throws DukeException {
        try {
            return input.split("/")[0].split(" ", 2)[1].trim();
        } catch (Exception e) {
            throw new DukeException("Hmm... You are lacking a \"name\" detail!");
        }
    }

    /**
     * This method gets the Deadline attribute
     *
     * @param byDate This is the String input
     * @return String This is the Deadline attribute
     * @throws DukeException On input error
     */
    public String getDeadlineAttribute(String byDate) throws DukeException {
        try {
            return byDate.split("/by ")[1];
        } catch (Exception e) {
            throw new DukeException("Hmm... You are lacking a \"/by\" detail!");
        }
    }

    /**
     * This method gets the Event attribute
     *
     * @param at This is the task attribute
     * @return String This is the Event attribute
     * @throws DukeException On input error.
     */
    public String getEventAttribute(String at) throws DukeException {
        try {
            return at.split("/at")[1].trim();
        } catch (Exception e) {
            throw new DukeException("Hmm... You are lacking \"/at\" detail!");
        }
    }

    /**
     * This method gets the Snooze attribute
     *
     * @param input This is the String input
     * @return String This is the Snooze Attribute
     * @throws DukeException On input error
     */
    public String getSnoozeAttribute(String input) throws DukeException {
        try {
            if (input.split(" ").length > 2) {
                return input.split("/to")[1].trim();
            } else {
                return String.valueOf(this.getIndex(input));
            }
        } catch (Exception e) {
            throw new DukeException("Snooze details incorrect, please check again");
        }
    }
}
