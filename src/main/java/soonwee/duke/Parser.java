package soonwee.duke;

import javafx.css.CssParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Parser instance. A Parser instance will validate input at
 * certain point.
 */
public class Parser {

    static final int MIN_LENGTH_FIND = 4; //Min string length for find.
    static final int MIN_LENGTH_TODO = 4; //Min string length for todo.
    static final int MIN_LENGTH_EVENT = 5; //Min string length for event.
    static final int MIN_LENGTH_DELETE = 7; //Min string length for delete including space.
    static final int MIN_LENGTH_DONE = 5; //Min string length for done including space.
    static final int MIN_LENGTH_DEADLINE = 8; //Min string length for deadline.
    static final int START_READ_FIND = 5; //Index to start reading for other details.
    static final int START_READ_TODO = 5; //Index to start reading for other details.
    static final int START_READ_EVENT = 6; //Index to start reading for other details.
    static final int START_READ_DEADLINE = 9; //Index to start reading for other details.

    /**
     * Instantiates Parser.
     */
    public Parser() {

    }

    /**
     * Checks if the delete and done input are valid
     *
     * @param input input string
     * @return validity of the string
     */
    public boolean isValidDeleteAndDoneInput(String input) {
        if (input.startsWith("delete") && input.length() > MIN_LENGTH_DELETE) {
            if (input.charAt(MIN_LENGTH_DELETE - 1) == ' ' && Character.isDigit(input.charAt(MIN_LENGTH_DELETE))) {
                try {
                    int index = Integer.parseInt(input.substring(MIN_LENGTH_DELETE));
                    return true;
                } catch (NumberFormatException ne) {
                    ne.getMessage();
                }
            }
        } else if (input.startsWith("done") && input.length() > MIN_LENGTH_DONE) {
            if (input.charAt(MIN_LENGTH_DONE - 1) == ' ' && Character.isDigit(input.charAt(MIN_LENGTH_DONE))) {
                try {
                    int index = Integer.parseInt(input.substring(MIN_LENGTH_DONE));
                    return true;
                } catch (NumberFormatException ne) {
                    ne.getMessage();
                }
            }
        }
        return false;
    }

    /**
     * Returns an integer that represent the type of task that is being passed
     * in.
     *
     * @param data command input by user
     * @return integer determining the task type
     */
    public TaskType checkTaskType(String data) {
        if (data.startsWith("todo") && data.charAt(START_READ_TODO - 1) == ' ') {
            return TaskType.TODO;
        } else if (data.indexOf("deadline") == 0 && data.startsWith("deadline") &&
                data.charAt(START_READ_DEADLINE - 1) == ' ') {
            return TaskType.DEADLINE;
        } else if (data.indexOf("event") == 0 && data.startsWith("event") &&
                data.charAt(START_READ_EVENT - 1) == ' ') {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }

    /**
     * Checks the front input of the command, followed by the description at
     * the back.
     *
     * @param cmd command input by user
     * @param taskType task identifier
     * @return task description
     */
    public String checkFrontInput(String cmd, TaskType taskType) {
        assert cmd != null : "Input command is null";
        String task = new String();
        String type = new String();
        try {
            if (taskType == TaskType.TODO) {
                type = "todo";
                if (cmd.length() > MIN_LENGTH_TODO) {
                    task = cmd.substring(START_READ_TODO);
                }
            } else if (taskType == TaskType.DEADLINE) {
                type = "deadline";
                int seg = cmd.indexOf("/");
                if (cmd.length() > MIN_LENGTH_DEADLINE && seg != -1) {
                    task = cmd.substring(START_READ_DEADLINE, seg);
                }
            } else if (taskType == TaskType.EVENT) {
                type = "event";
                int seg = cmd.indexOf("/");
                if (cmd.length() > MIN_LENGTH_EVENT && seg != -1) {
                    task = cmd.substring(START_READ_EVENT, seg);
                }
            }
            if (task.equals("") && !type.isEmpty()) {
                throw new DukeException("OOPS!!! " + "The description of a " + type + " cannot be empty.");
            } else if (task.equals("") && type.isEmpty()) {
                throw new DukeException("Please input the correct command.");
            }
        } catch (DukeException de) {
            task = de.getMessage();
        }
        return task;
    }

    /**
     * Gets the word that find is supposed to filter.
     *
     * @param cmd user input
     * @return search text to filter
     */
    public String getSearchWord(String cmd) {
        assert cmd != null : "Input command is null";
        String searchText = new String();
        try {
            if (cmd.length() > MIN_LENGTH_FIND) {
                if (cmd.charAt(MIN_LENGTH_FIND) == ' ') {
                    searchText = cmd.substring(START_READ_FIND);
                } else {
                    throw new DukeException("Invalid: Invalid command.");
                }
            } else {
                throw new DukeException("Invalid: Please type a white space, followed by description");
            }
        } catch (DukeException de) {
            searchText = de.getMessage();
        }
        return searchText;
    }

    /**
     * Takes in a command and returns the date and time in a LocalDateTime format.
     *
     * @param cmd command input by user
     * @return datetime in LocalDateTime format
     */
    public LocalDateTime dateFormatter(String cmd) {
        assert cmd != null : "Input command is null";
        int nextChar = -1; //Set by default to not able to find.
        int nextWord = 4; //Template number to find the next text in String.
        int nextSpace = 3; //Template number to find the next space in String.
        if (cmd.indexOf("/by") != -1 && cmd.length() > cmd.indexOf("/by") + nextSpace) {
            if (cmd.charAt(cmd.indexOf("/by") + 3) == ' ') {
                nextChar = cmd.indexOf("/by");
            } else {
                return null;
            }
        } else if (cmd.indexOf("/at") != -1 && cmd.length() > cmd.indexOf("/at") + nextSpace) {
            if (cmd.charAt(cmd.indexOf("/at") + 3) == ' ') {
                nextChar = cmd.indexOf("/at");
            } else {
                return null;
            }
        } else {
            return null;
        }
        try {
            String date = cmd.substring(nextChar + nextWord);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
            LocalDateTime formatDate = LocalDateTime.parse(date, formatter);
            return formatDate;
        } catch (DateTimeParseException de) {
            de.getMessage();
        }
        return null;
    }
}