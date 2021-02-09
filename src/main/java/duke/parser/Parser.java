package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandOption;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parses the user input and return corresponding Command objects for execution.
 */
public class Parser {

    /**
     * Constructor
     */
    public Parser() {

    }

    /**
     * returns the Task based on taskinfo stored in storage
     * @param taskInfo information stored in storage
     * @return the Task
     * @throws DukeException when information in storage is corrupted
     */
    public static Task stringToTask(String taskInfo) throws DukeException {
        String[] taskInfoArr = taskInfo.split("\\|");
        String type = taskInfoArr[0].strip();
        boolean isDone = taskInfoArr[1].strip().equals("1");
        String description = taskInfoArr[2].strip();

        Task task = null;
        switch(type) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "E":
            String at = taskInfoArr[3].strip();
            task = new Event(description, isDone, at);
            break;
        case "D":
            LocalDate by = parseByDate(taskInfoArr[3].strip());
            task = new Deadline(description, isDone, by);
            break;
        default:
            throw new DukeException("Invalid task info found in storage.");
        }
        return task;
    }

    /**
     * Parses the user input.
     * 1. Takes in the first word from user input and carries out relevant actions based on
     *      the word by printing out corresponding replies.
     * 2. A command is NOT case sensitive.
     *      For example, "LIST"/"list"/"List" will have the same effect.
     * 3. However, no additional whitespaces should be entered.
     *      For example, "LIST "/"list "/"List " will not work.
     * 4. Disclaimer: the idea of using .valueOf and convert to UpperCase is inspired
     *      based on discussion of #Issue 14 in forum.
     *      Credit to @samuelfangjw who mentioned it first.
     * @param userInput user input
     * @throws DukeException thrown if user enters a valid command but invalid related information
     *                       or an invalid command
     */
    public Command parseMessage(String userInput) throws DukeException {
        String[] processedUserInput = processUserInput(userInput);
        return getCommand(processedUserInput);
    }

    private String[] processUserInput(String userInput) {
        String[] msgArray = userInput.split(" ", 2);
        String commandWord = msgArray[0].strip();
        String otherInfo = null;
        boolean isDateProvided = msgArray.length > 1;
        if (isDateProvided) {
            otherInfo = msgArray[1].strip();
        }
        return new String[] {commandWord, otherInfo};
    }

    private Command getCommand(String[] processedUserInput) throws DukeException {
        String commandWord = processedUserInput[0];
        String otherInfo = processedUserInput[1];
        Command command;
        try {
            command = commandWordToCommand(commandWord, otherInfo);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I do not understand this command.");
        }
        return command;
    }

    private Command commandWordToCommand(String commandWord, String otherInfo)
            throws DukeException {
        Command command;
        CommandOption commandEnum = CommandOption.valueOf(commandWord.toUpperCase(Locale.ROOT));
        switch (commandEnum) {
        case LIST:
            command = new ListCommand();
            break;
        case DONE:
            command = preCompleteTask(otherInfo);
            break;
        case TODO:
            command = preTodoTask(otherInfo);
            break;
        case EVENT:
            command = preEventTask(otherInfo);
            break;
        case DEADLINE:
            command = preDeadlineTask(otherInfo);
            break;
        case DELETE:
            command = preDeleteTask(otherInfo);
            break;
        case FIND:
            command = preFindTask(otherInfo);
            break;
        case UPDATE:
            command = preUpdateTask(otherInfo);
            break;
        default:
            throw new DukeException("Unexpected value: " + commandEnum);
        }
        return command;
    }

    private Command preCompleteTask(String taskIndex) throws DukeException {
        int index = validateInteger(taskIndex);
        return new DoneCommand(index);
    }

    private Command preDeleteTask(String taskIndex) throws DukeException {
        int index = validateInteger(taskIndex);
        return new DeleteCommand(index);
    }

    private Command preTodoTask(String description) throws DukeException {
        if (description == null) {
            throw new DukeException("Please provide a description when creating todo.");
        }
        return new AddCommand(new Todo(description));
    }

    private Command preDeadlineTask(String otherInfo) throws DukeException {
        String[] validatedInfo = validateOtherInfo(otherInfo, "/by");
        String description = validatedInfo[0];
        LocalDate byDate = parseByDate(validatedInfo[1]);
        Task deadline = new Deadline(description, byDate);
        return new AddCommand(deadline);
    }

    private Command preEventTask(String otherInfo) throws DukeException {
        String[] validatedInfo = validateOtherInfo(otherInfo, "/at");
        String description = validatedInfo[0];
        String at = validatedInfo[1];
        Task event = new Event(description, at);
        return new AddCommand(event);
    }

    private Command preFindTask(String keyword) throws DukeException {
        if (keyword == null || keyword.equals(" ") || keyword.equals("")) {
            throw new DukeException("Please enter a keyword when finding the task.");
        }
        return new FindCommand(keyword);
    }

    private Command preUpdateTask(String otherInfo) throws DukeException {
        String[] validatedInfo = validateOtherInfoForUpdate(otherInfo);
        int taskIndexToUpdate = validateInteger(validatedInfo[0]);
        LocalDate byDate = parseByDate(validatedInfo[1]);
        return new UpdateCommand(taskIndexToUpdate, byDate);
    }

    private String[] validateOtherInfoForUpdate(String otherInfo) throws DukeException {
        String[] validatedOtherInfoForUpdate;
        String splitBy = " ";
        try {
            validatedOtherInfoForUpdate = getValidatedOtherInfo(otherInfo, splitBy);
            return validatedOtherInfoForUpdate;
        } catch (NullPointerException e) {
            throw new DukeException("Please provide the relevant information "
                    + "when updating a task.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide the required information or "
                + "ensure there is a whitespace between the task index and new date.");
        }
    }

    private String[] validateOtherInfo(String otherInfo, String splitBy) throws DukeException {
        String[] validatedOtherInfo;
        try {
            validatedOtherInfo = getValidatedOtherInfo(otherInfo, splitBy);
            return validatedOtherInfo;
        } catch (NullPointerException e) {
            throw new DukeException("Please provide the relevant information "
                    + "when creating a task.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Pleasure provide the required information or ensure "
                    + "/by is used when creating a deadline and "
                    + "/at is used when creating an event.");
        }
    }

    private String[] getValidatedOtherInfo(String otherInfo, String splitBy) throws DukeException {
        String[] splitOtherInfo = otherInfo.split(splitBy, 2);
        String description = splitOtherInfo[0].strip();
        String date = splitOtherInfo[1].strip();

        boolean noDescriptionOrDate = description.equals("") || date.equals("");
        if (noDescriptionOrDate) {
            throw new DukeException("Please provide the required information.");
        } else {
            return new String[] {description, date};
        }
    }

    private int validateInteger(String taskIndex) throws DukeException {
        int index;
        if (taskIndex == null) {
            throw new DukeException("Please enter a task index.");
        }

        try {
            index = Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Task index entered is not an integer.");
        }
        return index;
    }


    /**
     * parses the given by date for deadline
     * @param dateInString by date in String
     * @return a parsed local date object
     * @throws DukeException when given date is invalid
     */
    private static LocalDate parseByDate(String dateInString) throws DukeException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateInString);
            assert !dateInString.isEmpty() : "deadline cannot be created without a by date!";
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid deadline date given.");
        }
        return date;
    }
}
