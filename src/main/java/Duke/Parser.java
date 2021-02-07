package Duke;

import Duke.Commands.*;
import Duke.Exceptions.DukeException;
import Duke.Exceptions.EmptyDescriptionException;
import Duke.Exceptions.InvalidCommandException;
import Duke.Exceptions.InvalidDescriptionException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.ToDo;

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
        if (!(taskType.equals("todo") || taskType.equals("done") || taskType.equals("list") || taskType.equals("event")
                || taskType.equals("deadline") || taskType.equals("delete") || taskType.equals("bye")
                || taskType.equals("find"))) {
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
        String taskType = tokens[0];

        invalidCommandChecker(taskType);

        switch (taskType) {
        case "list": {
            return new PrintListCommand();
        }
        case "done": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];
            int taskIndex = Integer.parseInt(taskInfo) - 1;

            return new DoneCommand(taskIndex);
        }
        case "delete": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];
            int taskIndex = Integer.parseInt(taskInfo) - 1;

            return new DeleteCommand(taskIndex);
        }
        case "todo": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];

            return new AddCommand(new ToDo(taskInfo));
        }
        case "deadline": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];

            if (!(taskInfo.contains("/by"))) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String[] taskInfoArr = taskInfo.split(" /by ", 2);

            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String[] dateAndTime = taskInfoArr[1].split(" ");
            String date = parseDate(dateAndTime[0]);
            String time = parseTime(dateAndTime[1]);
            String by = date + " " + time;

            return new AddCommand(new Deadline(taskInfoArr[0], by));
        }
        case "event": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];

            if (!(taskInfo.contains("/at"))) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String[] taskInfoArr = taskInfo.split(" /at ", 2);

            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }

            String[] dateAndTime = taskInfoArr[1].split(" ");
            String date = parseDate(dateAndTime[0]);
            String time = parseTime(dateAndTime[1]);
            String by = date + " " + time;

            return new AddCommand(new Event(taskInfoArr[0], by));
        }
        case "find": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];

            return new FindCommand(taskInfo);
        }
        case "bye": {
            return new ExitCommand();
        }
        default: {
            return null;
        }
        }
    }
}
