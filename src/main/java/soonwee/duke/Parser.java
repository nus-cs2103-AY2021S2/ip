package soonwee.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Parser instance. A Parser instance will validate input at
 * certain point.
 */
public class Parser {

    /**
     * Instantiates Parser.
     */
    public Parser() {

    }

    /**
     * Returns an integer that represent the type of task that is being passed
     * in.
     *
     * @param data command input by user
     * @return integer determining the task type
     */
    public TaskType checkTaskType(String data) {
        if (data.indexOf("todo") == 0 && data.contains("todo")) {
            return TaskType.TODO;
        } else if (data.indexOf("deadline") == 0 && data.contains("deadline")) {
            return TaskType.DEADLINE;
        } else if (data.indexOf("event") == 0 && data.contains("event")) {
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
                if (cmd.length() > 4) {
                    task = cmd.substring(5);
                }
            } else if (taskType == TaskType.DEADLINE) {
                type = "deadline";
                int seg = cmd.indexOf("/");
                if (cmd.length() > 8 && seg != -1) {
                    task = cmd.substring(9, seg);
                }
            } else if (taskType == TaskType.EVENT) {
                type = "event";
                int seg = cmd.indexOf("/");
                if (cmd.length() > 5 && seg != -1) {
                    task = cmd.substring(6, seg);
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
            if (cmd.length() > 4) {
                if (cmd.charAt(4) == ' ') {
                    searchText = cmd.substring(5);
                } else {
                    throw new DukeException("Invalid: Invalid command.");
                }
            } else {
                throw new DukeException("Invalid: Please type a white space, followed by description");
            }
        } catch (DukeException de) {
            searchText = searchText + de.getMessage();
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
        int nextChar = -1;
        int nextWord = 4;
        if (cmd.indexOf("/by") != -1) {
            nextChar = cmd.indexOf("/by");
        } else if (cmd.indexOf("/at") != -1) {
            nextChar = cmd.indexOf("/at");
        }
        String date = cmd.substring(nextChar + nextWord);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime formatDate = LocalDateTime.parse(date, formatter);
        return formatDate;
    }
}