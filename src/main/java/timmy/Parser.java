package timmy;

import timmy.Commands.*;
import timmy.Exceptions.DukeException;
import timmy.Exceptions.EmptyDescriptionException;
import timmy.Exceptions.InvalidCommandException;
import timmy.Exceptions.InvalidDescriptionException;
import timmy.Tasks.Deadline;
import timmy.Tasks.Event;
import timmy.Tasks.Priority;
import timmy.Tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Takes in user input and makes sense of it for the program
 */
public class Parser {

    public Parser() {

    }

    /**
     * Checks whether the command is invalid
     *
     * @param taskType the first word of the input.
     * @throws InvalidCommandException is thrown when there is an error related to unknown command
     */
    void invalidCommandChecker(String taskType) throws InvalidCommandException {
        boolean isATask = taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event");
        boolean isACommand = taskType.equals("done") || taskType.equals("list")
                || taskType.equals("delete") || taskType.equals("find");

        if (!(isATask || isACommand)) {
            throw new InvalidCommandException("Sorry, I don't know what that means...");
        }
    }

    /**
     * Checks whether the command has an empty description
     *
     * @param tokens specifies an array of the user input
     * @throws EmptyDescriptionException is thrown when there is an error related to an empty description after a command
     */
    void emptyDescriptionChecker(String[] tokens) throws EmptyDescriptionException {
        if (tokens.length < 2) {
            throw new EmptyDescriptionException("Sorry, nothing was written after the command so I am unable to process...");
        }
    }

    /**
     * Converts the date to a suitable format so that the program is able to understand
     *
     * @param date Date in the format of yyyy-mm-dd
     * @return Returns the date in (MMM d yyyy) format in String
     */
    public String parseDate(String date) {
        LocalDate d1 = LocalDate.parse(date);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts the time to a suitable format so that the program is able to understand
     *
     * @param time time in the format of (hh:mm) in 24 hour time
     * @return Returns the time in (hh:mm a) format in String
     */
    public String parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                .format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Makes sense of the command given in string for the program to be able to take action
     *
     * @param fullCommand the entire string of the user input
     * @return Returns a <code>Duke.Duke.Commands.Command</code> that specifies what the user is asking the program to do
     * @throws DukeException is thrown when there is an error related to duke
     */
    public Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.split(" ", 2);
        assert tokens.length > 0;

        String taskType = tokens[0];

        invalidCommandChecker(taskType);
        return getCommandType(tokens, taskType);
    }

    /**
     * Returns a command after making sense of the String array of tokens
     *
     * @param tokens   String array that represents the full command
     * @param taskType Part of the command
     * @return A command that represents the string array of tokens
     * @throws EmptyDescriptionException   An exception that occurs when there is an empty description
     * @throws InvalidDescriptionException An exception that occurs when there is an invalid description
     * @throws InvalidCommandException     An exception that occurs when there is an invalid command
     */
    private Command getCommandType(String[] tokens, String taskType) throws EmptyDescriptionException,
            InvalidDescriptionException, InvalidCommandException {
        switch (taskType) {
        case "list": {
            return new PrintListCommand();
        }
        case "done": {
            emptyDescriptionChecker(tokens);
            int taskIndex = getTaskIndex(tokens[1]);

            return new DoneCommand(taskIndex);
        }
        case "delete": {
            emptyDescriptionChecker(tokens);
            int taskIndex = getTaskIndex(tokens[1]);

            return new DeleteCommand(taskIndex);
        }
        case "todo": {
            emptyDescriptionChecker(tokens);

            String[] nextTaskInfo = getTaskInfo(tokens[1]);

            switch (nextTaskInfo[0]) {
            case "H":
                return new AddCommand(new ToDo(Priority.HIGH, nextTaskInfo[1]));
            case "M":
                return new AddCommand(new ToDo(Priority.MEDIUM, nextTaskInfo[1]));
            case "L":
                return new AddCommand(new ToDo(Priority.LOW, nextTaskInfo[1]));
            default:
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }
        }
        case "deadline": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];

            if (!(taskInfo.contains("/by"))) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String[] nextTaskInfo = tokens[1].split(" ", 2);
            Priority priority = Priority.NONE;

            if (!(nextTaskInfo[0].equals("H") || nextTaskInfo[0].equals("M") || nextTaskInfo[0].equals("L"))) {
                throw new InvalidDescriptionException("Sorry, invalid priority!");
            }

            priority = getPriority(nextTaskInfo, priority);

            String[] taskInfoArr = nextTaskInfo[1].split(" /by ", 2);

            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String by = getDate(taskInfoArr[1]);

            return new AddCommand(new Deadline(priority, taskInfoArr[0], by));
        }
        case "event": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];
            String[] nextTaskInfo = taskInfo.split(" ", 2);
            Priority priority = Priority.NONE;

            if (!(nextTaskInfo[0].equals("H") || nextTaskInfo[0].equals("M") || nextTaskInfo[0].equals("L"))) {
                throw new InvalidDescriptionException("Sorry, invalid priority!");
            }

            priority = getPriority(nextTaskInfo, priority);

            if (!(taskInfo.contains("/at"))) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String[] taskInfoArr = nextTaskInfo[1].split(" /at ", 2);


            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String by = getDate(taskInfoArr[1]);

            return new AddCommand(new Event(priority, taskInfoArr[0], by));
        }
        case "find": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];

            return new FindCommand(taskInfo);
        }
        default: {
            throw new InvalidCommandException("Sorry, I don't know what that means...");
        }
        }
    }

    private Priority getPriority(String[] nextTaskInfo, Priority priority) {
        switch (nextTaskInfo[0]) {
        case "H":
            priority = Priority.HIGH;
            break;
        case "M":
            priority = Priority.MEDIUM;
            break;
        case "L":
            priority = Priority.LOW;
            break;
        default:
            break;
        }
        return priority;
    }

    private String[] getTaskInfo(String token) throws InvalidDescriptionException {
        String[] nextTaskInfo = token.split(" ", 2);

        if (!(nextTaskInfo[0].equals("H") || nextTaskInfo[0].equals("M") || nextTaskInfo[0].equals("L"))) {
            throw new InvalidDescriptionException("Sorry, invalid priority!");
        }
        return nextTaskInfo;
    }

    private String getDate(String s) {
        String[] dateAndTime = s.split(" ");
        String date = parseDate(dateAndTime[0]);
        String time = parseTime(dateAndTime[1]);
        return date + " " + time;
    }

    private int getTaskIndex(String taskInfo) {
        return Integer.parseInt(taskInfo) - 1;
    }
}
